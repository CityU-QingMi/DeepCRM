	protected void init(boolean audited, String auditStrategy) throws IOException {
		this.audited = audited;

		Properties configurationProperties = new Properties();
		configurationProperties.putAll( Environment.getProperties() );
		if ( !audited ) {
			configurationProperties.setProperty( EnversIntegrator.AUTO_REGISTER, "false" );
		}
		if ( createSchema() ) {
			configurationProperties.setProperty( Environment.HBM2DDL_AUTO, "create-drop" );
			configurationProperties.setProperty( Environment.USE_NEW_ID_GENERATOR_MAPPINGS, "true" );
			configurationProperties.setProperty( EnversSettings.USE_REVISION_ENTITY_WITH_NATIVE_ID, "false" );
		}
		if ( auditStrategy != null && !"".equals( auditStrategy ) ) {
			configurationProperties.setProperty( "org.hibernate.envers.audit_strategy", auditStrategy );
		}

		addConfigurationProperties( configurationProperties );

		configurationProperties.put( AvailableSettings.LOADED_CLASSES, Arrays.asList( getAnnotatedClasses() ) );

		entityManagerFactoryBuilder = (EntityManagerFactoryBuilderImpl) Bootstrap.getEntityManagerFactoryBuilder(
				new PersistenceUnitDescriptorAdapter(),
				configurationProperties
		);

		emf = entityManagerFactoryBuilder.build().unwrap( HibernateEntityManagerFactory.class );

		serviceRegistry = (StandardServiceRegistryImpl) emf.getSessionFactory()
				.getServiceRegistry()
				.getParentServiceRegistry();

		newEntityManager();
	}
