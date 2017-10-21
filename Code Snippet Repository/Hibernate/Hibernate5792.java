	@Override
	protected Map getConfig() {
		Map config = super.getConfig();
		config.put( AvailableSettings.DISCARD_PC_ON_CLOSE, "true");
		config.put(
				org.hibernate.cfg.AvailableSettings.CONNECTION_PROVIDER,
				connectionProvider
		);
		return config;
	}
