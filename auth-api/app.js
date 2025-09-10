import express from 'express';
import * as initialConfigData from './src/config/db/initialData.js';
import userRoutes from './src/modules/user/routes/UserRoutes.js';

const app = express();
const env = process.env;
const PORT = env.PORT || 8080;

// initialConfigData.createInitialData();

app.use(express.json());

app.get('/api/status', (req, res) => {
  return res.status(200).json({
    service: 'auth-api',
    version: '1.0.0',
    status: 'up',
    httpStatus: 200,
  });
});
app.use(userRoutes);

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});