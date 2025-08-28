import UserService from "../service/UserService.js";

class UserController {
  async findByEmail(req, res) {
    try {
      const result = await UserService.findByEmail(req);
      return res.status(result.status).json(result.user);
    } catch (error) {
      return res.status(error.status).json({
        message: error.message
      });
    }
  }
}

export default new UserController();