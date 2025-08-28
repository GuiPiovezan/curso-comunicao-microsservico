import User from '../model/User.js';

class UserRepository {

  async findById(id) {
    try {
      return await User.findOne({ where: { id } });
    } catch (error) {
      throw new Error(error.message);
    }
  }

  async findByEmail(email) {
    try {
      return await User.findOne({ where: { email } });
    } catch (error) {
      throw new Error(error.message);
    }
  }

}

export default new UserRepository();