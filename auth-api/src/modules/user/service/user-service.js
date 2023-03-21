import UserRepository from "../repository/user-repository.js";
import * as httpStatus from '../../../config/constants/httpStatus.js';
import UserException from "../exception/user-exception.js";
import * as secrets from "../../../config/constants/secrets.js"

import bcrypt from "bcrypt";
import jwt from 'jsonwebtoken';

class UserServices {

  async findByEmail(req) {
    try {
      const { email } = req.params;
      const { authUser } = req;

      this.validateRequestData(email);

      let user = await UserRepository.findByEmail(email);

      this.valideUserNotFound(user);
      this.validateAuthenticatedUser(user, authUser);

      return {
        status: httpStatus.SUCCESS,
        user: {
          id: user.id,
          name: user.name,
          email: user.email
        }
      }
    } catch (error) {
      return {
        status: error.status ? error.status : httpStatus.INTERNAL_SERVER_ERROR,
        message: error.message
      }
    }
  }

  validateRequestData(email) {
    if(!email) {
      throw new UserException(httpStatus.BAD_REQUEST, 'User email was not informed.');
    }
  }

  valideUserNotFound(user) {
    if(!user){
      throw new UserException(httpStatus.BAD_REQUEST, 'User was not informed.');
    }
  }

  validateAuthenticatedUser(user, authUser){
    if (!authUser || (user.id !== authUser.id)) {
      throw new UserException(httpStatus.FORBIDDEN, "You cannot see this user data.");
    }
  }

  async getAccessToken(req) {
    try {
      const { email, password } = req.body;

      this.validateAcessTokenData(email, password);
      let user = await UserRepository.findByEmail(email);
      this.valideUserNotFound(user);

      await this.validatePassword(password, user.password);
      const authUser = { id: user.id, name: user.name, email: user.email };
      const accessToken = jwt.sign({authUser}, secrets.API_SECRET ,{expiresIn: '1d'});

      return {
        status: httpStatus.SUCCESS,
        accessToken,
      }
    } catch (error) {
      
    }
    
  }

  validateAcessTokenData (email, password) {
    if(!email || !password){
      throw new UserException(httpStatus.UNAUTHORIZED, "Email and password must be informed.")
    }
  }

  async validatePassword(password, hashPassword) {
    if(!await bcrypt.compare(password, hashPassword)) {
      throw new UserException(httpStatus.UNAUTHORIZED, "Password doesn't match.");
    }
  }

}

export default new UserServices();