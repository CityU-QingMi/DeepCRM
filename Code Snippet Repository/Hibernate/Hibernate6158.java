	private Map getConfig() {
		final Map<Object, Object> config = Environment.getProperties();
		config.put( org.hibernate.cfg.AvailableSettings.HBM2DDL_SCRIPTS_CREATE_TARGET, createSchema.toPath() );
		config.put( org.hibernate.cfg.AvailableSettings.HBM2DDL_SCRIPTS_DROP_TARGET, dropSchema.toPath() );
		config.put( org.hibernate.cfg.AvailableSettings.HBM2DDL_SCRIPTS_ACTION, "drop-and-create" );
		ArrayList<Class> classes = new ArrayList<Class>();

		classes.addAll( Arrays.asList( new Class[] {TestEntity.class} ) );
		config.put( org.hibernate.jpa.AvailableSettings.LOADED_CLASSES, classes );
		return config;
	}
