	private static PersistenceDescriptor persistenceXml() {
		final PersistenceDescriptor pd = Descriptors.create( PersistenceDescriptor.class )
				.version( "2.1" )
				.createPersistenceUnit().name( PERSISTENCE_UNIT_NAME )
				.transactionType( PersistenceUnitTransactionType._JTA )
				.jtaDataSource( "java:jboss/datasources/ExampleDS" )
				.clazz( WildFlyDdlEntity.class.getName() )
				.excludeUnlistedClasses( true )
				.getOrCreateProperties().createProperty().name( "jboss.as.jpa.providerModule" ).value( "org.hibernate:5.2" ).up().up()
				.getOrCreateProperties().createProperty().name( "hibernate.hbm2ddl.auto" ).value( "create-drop" ).up().up()
				// this should not be needed, but...
				.getOrCreateProperties().createProperty().name( AvailableSettings.JTA_PLATFORM ).value( JBossAppServerJtaPlatform.class.getName() ).up().up()
				.up();


		System.out.println( "persistence.xml: " );
		pd.exportTo( System.out );

		return pd;
	}
