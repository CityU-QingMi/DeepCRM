    @PluginFactory
    public static MongoDbProvider createNoSqlProvider(
            final String collectionName,
            final String writeConcernConstant,
            final String writeConcernConstantClassName,
            final String databaseName,
            final String server,
            final String port,
            final String userName,
            final String password,
            final String factoryClassName,
			final String factoryMethodName) {
    	LOGGER.info("createNoSqlProvider");
		return newBuilder().setCollectionName(collectionName).setWriteConcernConstant(writeConcernConstantClassName)
				.setWriteConcernConstant(writeConcernConstant).setDatabaseName(databaseName).setServer(server)
				.setPort(port).setUserName(userName).setPassword(password).setFactoryClassName(factoryClassName)
				.setFactoryMethodName(factoryMethodName).build();
	}
