	private static PersistenceDescriptor persistenceXml() {
		return Descriptors.create( PersistenceDescriptor.class )
				.createPersistenceUnit().name( "pu-cdi-basic" )
				.clazz( MyEntity.class.getName() )
				.excludeUnlistedClasses( true )
				.nonJtaDataSource( "java:jboss/datasources/ExampleDS" )
				.getOrCreateProperties().createProperty().name( "jboss.as.jpa.providerModule" ).value( "org.hibernate:5.2" ).up().up()
				.getOrCreateProperties().createProperty().name( "hibernate.delay_cdi_access" ).value( "true" ).up().up()
				.getOrCreateProperties().createProperty().name( "hibernate.hbm2ddl.auto" ).value( "create-drop" ).up().up().up();
	}
