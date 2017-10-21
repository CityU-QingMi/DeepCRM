	@Override
	protected void afterStandardServiceRegistryBuilt(StandardServiceRegistry ssr) {
		if ( bindToJndi ) {
			try {
				// Create an in-memory jndi
				namingServer = new SingletonNamingServer();
				namingMain = new Main();
				namingMain.setInstallGlobalService( true );
				namingMain.setPort( -1 );
				namingMain.start();
				props = new Properties();
				props.put( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
				props.put( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );

				final String cfgFileName = (String) ssr.getService( ConfigurationService.class ).getSettings().get(
						InfinispanRegionFactory.INFINISPAN_CONFIG_RESOURCE_PROP
				);
				manager = new DefaultCacheManager(
						cfgFileName == null ? InfinispanRegionFactory.DEF_INFINISPAN_CONFIG_RESOURCE : cfgFileName,
						false
				);
				Context ctx = new InitialContext( props );
				bind( JNDI_NAME, manager, EmbeddedCacheManager.class, ctx );
			}
			catch (Exception e) {
				throw new RuntimeException( "Failure to set up JNDI", e );
			}
		}
	}
