	private SessionFactory buildSessionFactory() {
		// Extra options located in src/test/resources/hibernate.properties
		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder()
				  .applySetting( Environment.DIALECT, "HSQL" )
				  .applySetting( Environment.HBM2DDL_AUTO, "create-drop" )
				  .applySetting( Environment.CONNECTION_PROVIDER, JtaAwareConnectionProviderImpl.class.getName() )
				  .applySetting( Environment.JNDI_CLASS, "org.jnp.interfaces.NamingContextFactory" )
				  .applySetting( Environment.TRANSACTION_COORDINATOR_STRATEGY, JtaTransactionCoordinatorBuilderImpl.class.getName() )
				  .applySetting( Environment.CURRENT_SESSION_CONTEXT_CLASS, "jta" )
				  .applySetting( Environment.RELEASE_CONNECTIONS, "auto" )
				  .applySetting( Environment.USE_SECOND_LEVEL_CACHE, "true" )
				  .applySetting( Environment.USE_QUERY_CACHE, "true" )
				  .applySetting( Environment.JTA_PLATFORM, new JBossStandAloneJtaPlatform() )
				  .applySetting( Environment.CACHE_REGION_FACTORY, TestInfinispanRegionFactory.class.getName() );

		StandardServiceRegistry serviceRegistry = ssrb.build();

		MetadataSources metadataSources = new MetadataSources( serviceRegistry );
		metadataSources.addResource("org/hibernate/test/cache/infinispan/functional/entities/Item.hbm.xml");

		Metadata metadata = metadataSources.buildMetadata();
		for ( PersistentClass entityBinding : metadata.getEntityBindings() ) {
			if ( entityBinding instanceof RootClass ) {
				( (RootClass) entityBinding ).setCacheConcurrencyStrategy( "transactional" );
			}
		}
		for ( Collection collectionBinding : metadata.getCollectionBindings() ) {
			collectionBinding.setCacheConcurrencyStrategy( "transactional" );
		}

		return metadata.buildSessionFactory();
	}
