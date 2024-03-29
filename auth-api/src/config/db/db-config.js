import Sequelize from "sequelize";

const sequelize = new Sequelize("auth-db", "admin", "123456", {
	host: "localhost",
	dialect: "postgres",
	quoteIdentifiers: false,
	define: {
			timestamps: false,
			underscored: true,
			freezeTableName: true,
	}
});

sequelize.authenticate().then(() => {
	console.info("Connection has been stablished!")
})
.catch((error) => {
	console.error("Unable to connect to the database");
	console.error(error.message);
});

export default sequelize;
