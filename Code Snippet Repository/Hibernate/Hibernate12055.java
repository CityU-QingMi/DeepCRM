	protected final StandardServiceRegistryBuilder constructStandardServiceRegistryBuilder() {
		final BootstrapServiceRegistryBuilder bsrb = new BootstrapServiceRegistryBuilder();
		bsrb.applyClassLoader( getClass().getClassLoader() );
		// by default we do not share the BootstrapServiceRegistry nor the StandardServiceRegistry,
		// so we want the BootstrapServiceRegistry to be automatically closed when the
		// StandardServiceRegistry is closed.
		bsrb.enableAutoClose();
		configureBootstrapServiceRegistryBuilder( bsrb );

		final BootstrapServiceRegistry bsr = bsrb.build();
		afterBootstrapServiceRegistryBuilt( bsr );

		final Map settings = new HashMap();
		addSettings( settings );

		final StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder( bsr );
		initialize( ssrb );
		ssrb.applySettings( settings );
		configureStandardServiceRegistryBuilder( ssrb );
		return ssrb;
	}
