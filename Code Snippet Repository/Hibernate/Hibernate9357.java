	@Before
	public void buildInitialSchema() throws Exception {
		// Builds the initial table in the schema.
		StandardServiceRegistry ssr = null;
		try {
			final Configuration cfg = buildConfiguration( SimpleFirst.class );
			ssr = new StandardServiceRegistryBuilder(
							new BootstrapServiceRegistryBuilder().build(),
							cfg.getStandardServiceRegistryBuilder().getAggregatedCfgXml() )
					.applySettings( cfg.getProperties() )
					.build();
			cfg.buildSessionFactory( ssr ).close();
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
