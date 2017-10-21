	public CatalogFilterTest() {
		Map settings = new HashMap();
		settings.putAll( Environment.getProperties() );
		settings.put( AvailableSettings.DIALECT, SQLServerDialect.class.getName() );
		settings.put( AvailableSettings.FORMAT_SQL, false );

		this.serviceRegistry = ServiceRegistryBuilder.buildServiceRegistry( settings );

		MetadataSources ms = new MetadataSources( serviceRegistry );
		ms.addAnnotatedClass( CatalogNoneEntity0.class );
		ms.addAnnotatedClass( Catalog1Entity1.class );
		ms.addAnnotatedClass( Catalog1Entity2.class );
		ms.addAnnotatedClass( Catalog2Entity3.class );
		ms.addAnnotatedClass( Catalog2Entity4.class );
		this.metadata = ms.buildMetadata();
	}
