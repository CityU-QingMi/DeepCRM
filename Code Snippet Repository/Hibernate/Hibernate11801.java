	private static PersistenceDescriptor persistenceXml() {
		return Descriptors.create( PersistenceDescriptor.class )
					.version( "2.1" )
					.createPersistenceUnit()
						.name( "primary" )
						.transactionType( PersistenceUnitTransactionType._JTA )
						.jtaDataSource( "java:jboss/datasources/ExampleDS" )
						.getOrCreateProperties()
							// We want to use the ORM from this build instead of the one coming with WildFly
							.createProperty().name( "jboss.as.jpa.providerModule" ).value( "org.hibernate:" + ORM_MINOR_VERSION ).up()
							.createProperty().name( "hibernate.hbm2ddl.auto" ).value( "create-drop" ).up()
							.createProperty().name( "hibernate.allow_update_outside_transaction" ).value( "true" ).up()
					.up().up();
	}
