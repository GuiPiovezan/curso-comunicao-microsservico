import express from 'express';

const app = express();
const env = process.env;
const PORT = env.PORT || 8080;

app.get('/', (req, res) => {
  return res.status(200).json({
    service: 'auth-api',
    version: '1.0.0',
    status: 'up',
    statusCode: 200,
  });
});

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});