	public SchemaFilterTest() {
		Map settings = new HashMap();
		settings.putAll( Environment.getProperties() );
		settings.put( AvailableSettings.DIALECT, SQLServerDialect.class.getName() );
		settings.put( AvailableSettings.FORMAT_SQL, false );

		this.serviceRegistry = ServiceRegistryBuilder.buildServiceRegistry( settings );

		MetadataSources ms = new MetadataSources( serviceRegistry );
		ms.addAnnotatedClass( SchemaNoneEntity0.class );
		ms.addAnnotatedClass( Schema1Entity1.class );
		ms.addAnnotatedClass( Schema1Entity2.class );
		ms.addAnnotatedClass( Schema2Entity3.class );
		ms.addAnnotatedClass( Schema2Entity4.class );
		this.metadata = ms.buildMetadata();
	}
