	@Override
	public void initializeWithMetaData(DatabaseMetaData databaseMetaData) throws SQLException {
		super.initializeWithMetaData(databaseMetaData);
		if (!databaseMetaData.supportsGetGeneratedKeys()) {
			if (logger.isWarnEnabled()) {
				logger.warn("Overriding supportsGetGeneratedKeys from DatabaseMetaData to 'true'; it was reported as " +
						"'false' by " + databaseMetaData.getDriverName() + " " + databaseMetaData.getDriverVersion());
			}
			this.supportsGeneratedKeysOverride = true;
		}
	}
