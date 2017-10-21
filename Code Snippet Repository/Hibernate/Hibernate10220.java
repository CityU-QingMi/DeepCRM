	@BeforeClassOnce
	public void init() throws URISyntaxException {
		config = new Configuration();
		URL url = Thread.currentThread().getContextClassLoader().getResource( getHibernateConfigurationFileName() );
		config.configure( new File( url.toURI() ) );

		String auditStrategy = getAuditStrategy();
		if ( auditStrategy != null && !"".equals( auditStrategy ) ) {
			config.setProperty( EnversSettings.AUDIT_STRATEGY, auditStrategy );
		}
		config.setProperty( Environment.USE_NEW_ID_GENERATOR_MAPPINGS, "true" );
		config.setProperty( EnversSettings.USE_REVISION_ENTITY_WITH_NATIVE_ID, "false" );
		addProperties( config );

		this.initMappings();

		serviceRegistry = ServiceRegistryBuilder.buildServiceRegistry( config.getProperties() );
		sessionFactory = config.buildSessionFactory( serviceRegistry );
	}
