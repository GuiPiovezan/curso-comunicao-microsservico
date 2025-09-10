import bcrypt from 'bcrypt';
import User from '../../modules/user/model/User.js';

export async function createInitialData() {
  User.sync();

  let password = await bcrypt.hash("123456", 10);

  await User.create({
    name: "Admin",
    email: "admin@example.com",
    password: password
  });
}