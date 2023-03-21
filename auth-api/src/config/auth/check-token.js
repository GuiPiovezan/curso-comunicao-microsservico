import jwt from "jsonwebtoken";
import { promisify } from 'util';

import * as secrets from "../constants/secrets.js";
import * as httpStatus from "../constants/httpStatus.js"
import AccessTokenException from "./access-token-exception.js"

const bearer = "bearer ";
const emptySpace = " ";

export default async (req, res, next) => {
  try {
    const { authorization } = req.headers;

    if(!authorization) {
      throw new AccessTokenException(httpStatus.UNAUTHORIZED, "Access token was not informed.");
    }

    let accessToken = authorization;

    if(accessToken.includes(emptySpace)) {
      accessToken = accessToken.split(emptySpace)[1];
    } else {
      accessToken = authorization;
    }

    const decoded = await promisify(jwt.verify)(accessToken, secrets.API_SECRET);

    req.authUser = decoded.authUser;

    return next();
  } catch (error) {
    const status = error.status ? error.status : httpStatus.INTERNAL_SERVER_ERROR;
    return res.status(status).json({
      status,
      message: error.message
    });
  }
  
}