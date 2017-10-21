	@Before
	public void setUp() {
		cfg = new Configuration();

		// HHH-10290 ignore environment property hibernate.jdbc.batch_versioned_data
		if (cfg.getProperties().getProperty(AvailableSettings.BATCH_VERSIONED_DATA) != null) {
			cfg.getProperties().remove(AvailableSettings.BATCH_VERSIONED_DATA);
			cfg.getStandardServiceRegistryBuilder().getSettings().remove(AvailableSettings.BATCH_VERSIONED_DATA);
		}
	}
