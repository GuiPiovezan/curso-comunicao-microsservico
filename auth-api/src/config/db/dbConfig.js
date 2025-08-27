import { Sequelize } from "sequelize";

const sequelize = new Sequelize("auth_db", "admin", "123456", {
  host: "localhost",
  dialect: "postgres",
  quoteIdentifiers: false, // Desabilita a alteração da nomenclatura de objetos, propriedades, classes...
  define: {
    underscored: false,
    freezeTableName: true, // Congelar o nome das tabelas
    timestamps: false, // Desabilita os campos createdAt e updatedAt
  }
});

sequelize.sync({alter: true}); // Altera as tabelas para ficarem iguais aos modelos

sequelize.authenticate()
  .then(() => {
    console.info("Connection has been established successfully.");
  })
  .catch((error) => {
    console.error("Unable to connect to the database:", error);
  });

export default sequelize;
