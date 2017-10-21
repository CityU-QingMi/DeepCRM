	@Override
	protected Dialect getDialect() {
		// if the environment is any version of SQLServerDialect, force the legacy SQLServerDialect instead
		// This is so that the legacy's TopLimitHandler will be used here to test the fix necessary when a
		// user explicitly configures the legacy dialect but uses a more modern version of SQL Server.
		final Dialect environmentDialect = super.getDialect();
		if ( environmentDialect instanceof SQLServerDialect ) {
			return new SQLServerDialect();
		}
		return environmentDialect;
	}
