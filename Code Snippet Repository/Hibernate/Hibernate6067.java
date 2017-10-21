	@Override
	protected Map getConfig() {
		Map config = super.getConfig();
		config.put(
				org.hibernate.cfg.AvailableSettings.CONNECTION_PROVIDER,
				connectionProvider
		);
		config.put(
				AvailableSettings.USE_SQL_COMMENTS,
				Boolean.TRUE.toString()
		);
		return config;
	}
