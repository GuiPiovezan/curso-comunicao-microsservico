import UserRepository from "../repository/UserRepository.js";
import * as httpStatusCode from '../../../config/constants/HttpStatusCode.js';
import UserException from "../exception/UserException.js";

class UserService {
  
  async findByEmail(req) {
    try {
      const { email } = req.params;
      this.validateRequestData(email);
      let user = await UserRepository.findByEmail(email);
      this.validateUserNotFound(user);
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
  
}

export default new UserService();