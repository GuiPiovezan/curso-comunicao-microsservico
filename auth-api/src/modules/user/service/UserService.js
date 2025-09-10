import bcrypt from 'bcrypt';
import jwt from 'jsonwebtoken';

import UserRepository from "../repository/UserRepository.js";
import * as httpStatusCode from '../../../config/constants/HttpStatusCode.js';
import UserException from "../exception/UserException.js";
import * as secrets from '../../../config/constants/secrets.js';

class UserService {

  async getAccessToken(req) {
    try {
      const { email, password } = req.body;
      this.validateAccessTokenData(email, password);
      let user = await UserRepository.findByEmail(email);
      this.validateUserNotFound(user);
      await this.validatePassword(password, user.password);
      const authUser = {
        id: user.id,
        name: user.name,
        email: user.email
      };
      const token = jwt.sign(authUser, secrets.API_SECRET, { expiresIn: '1d' });
      return {
        status: httpStatusCode.SUCCESS,
        token
      };
    } catch (error) {
      throw {
        status: error.status || httpStatusCode.INTERNAL_SERVER_ERROR,
        message: error.message
      }; 
    }
  }

  validateAccessTokenData(email, password) {
    if (!email || !password) {
      throw new UserException('Email and password are required.', httpStatusCode.UNAUTHORIZED);
    }
  }

  async validatePassword(password, hashedPassword) { 
    if (!await bcrypt.compare(password, hashedPassword)) {
      throw new UserException('Invalid password.', httpStatusCode.UNAUTHORIZED);
    }
  }

  async findByEmail(req) {
    try {
      const { email } = req.params;
      const { authUser } = req;
      this.validateRequestData(email);
      let user = await UserRepository.findByEmail(email);
      this.validateUserNotFound(user);
      this.validateUserAuthenticated(user, authUser);
      return {
        status: httpStatusCode.SUCCESS,
        user: {
          id: user.id,
          name: user.name,
          email: user.email
        }
      };
    } catch (error) {
      throw {
        status: error.status || httpStatusCode.INTERNAL_SERVER_ERROR,
        message: error.message || 'Internal Server Error'
      };
    }
  }
  
  validateRequestData(email) {
    if (!email) {
      throw new UserException('User email is required', httpStatusCode.BAD_REQUEST);
    }
  }

  validateUserNotFound(user) {
    if (!user) {
      throw new UserException('User not found', httpStatusCode.NOT_FOUND);
    }
  }

  validateUserAuthenticated(user, authUser) {
    if (!authUser || (user.id !== authUser.id)) {
      throw new UserException('You are not authorized to access this resource.', httpStatusCode.FORBIDDEN);
    }
  }
  
}

export default new UserService();