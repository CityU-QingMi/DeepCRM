	@Override
	protected void addSettings(Map settings) {
		settings.put(
				AvailableSettings.CONNECTION_PROVIDER,
				connectionProvider
		);
		settings.put(
				AvailableSettings.JDBC_TIME_ZONE,
				TIME_ZONE
		);
	}
