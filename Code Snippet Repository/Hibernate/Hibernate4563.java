	private PhysicalConnectionHandlingMode determineConnectionHandlingMode(
			PhysicalConnectionHandlingMode connectionHandlingMode,
			JdbcConnectionAccess jdbcConnectionAccess) {
		if ( connectionHandlingMode.getReleaseMode() == ConnectionReleaseMode.AFTER_STATEMENT
				&& !jdbcConnectionAccess.supportsAggressiveRelease() ) {
			return PhysicalConnectionHandlingMode.DELAYED_ACQUISITION_AND_RELEASE_AFTER_TRANSACTION;
		}

		return connectionHandlingMode;
	}
