	public static EmbeddedDatabaseConfigurer getConfigurer(EmbeddedDatabaseType type) throws IllegalStateException {
		Assert.notNull(type, "EmbeddedDatabaseType is required");
		try {
			switch (type) {
				case HSQL:
					return HsqlEmbeddedDatabaseConfigurer.getInstance();
				case H2:
					return H2EmbeddedDatabaseConfigurer.getInstance();
				case DERBY:
					return DerbyEmbeddedDatabaseConfigurer.getInstance();
				default:
					throw new UnsupportedOperationException("Embedded database type [" + type + "] is not supported");
			}
		}
		catch (ClassNotFoundException ex) {
			throw new IllegalStateException("Driver for test database type [" + type +
					"] is not available in the classpath", ex);
		}
	}
