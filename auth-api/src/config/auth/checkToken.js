// Middlewares são funções que processam requisições antes de chegarem nas rotas
import jwt from "jsonwebtoken";
import  { promisify } from "util";

import AuthException from "./AuthException.js";

import * as secrets from '../constants/secrets.js';
import * as httpStatusCode from '../constants/HttpStatusCode.js'; 

const bearer = "bearer ";

export default async (req, res, next) => {
  try {
    const { authorization } = req.headers;
    if (!authorization) {
      throw new AuthException('Access token is missing or invalid.', httpStatusCode.UNAUTHORIZED);
    }
    
    let token = authorization;
    if (token.toLowerCase().includes(bearer)) {
      token = token.replace(bearer, '');
    }
    const decoded = await promisify(jwt.verify)(token, secrets.API_SECRET);
    req.authUser = decoded.authUser;
    return next();
  } catch (error) {
    return res.status(error.status || httpStatusCode.INTERNAL_SERVER_ERROR).json({
      status: error.status || httpStatusCode.INTERNAL_SERVER_ERROR,
      message: error.message
    });
  }
};