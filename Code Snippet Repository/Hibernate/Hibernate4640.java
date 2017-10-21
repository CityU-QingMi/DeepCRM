	public AbstractServiceRegistryImpl(
			BootstrapServiceRegistry bootstrapServiceRegistry,
			boolean autoCloseRegistry) {
		if ( ! ServiceRegistryImplementor.class.isInstance( bootstrapServiceRegistry ) ) {
			throw new IllegalArgumentException( "ServiceRegistry parent needs to implement ServiceRegistryImplementor" );
		}
		this.parent = (ServiceRegistryImplementor) bootstrapServiceRegistry;
		this.allowCrawling = ConfigurationHelper.getBoolean( ALLOW_CRAWLING, Environment.getProperties(), true );

		this.autoCloseRegistry = autoCloseRegistry;
		this.parent.registerChild( this );
	}
