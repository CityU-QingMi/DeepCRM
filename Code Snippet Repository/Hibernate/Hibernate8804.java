	@Test
	public void testReleaseMode() {
		ServiceRegistryImplementor serviceRegistry = null;
		try {
			serviceRegistry	= (ServiceRegistryImplementor) new StandardServiceRegistryBuilder()
					.applySetting( Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA  )
					.applySetting( Environment.RELEASE_CONNECTIONS, ConnectionReleaseMode.AFTER_STATEMENT.name() )
					.addService(
							MultiTenantConnectionProvider.class,
							new TestingConnectionProvider(
									new TestingConnectionProvider.NamedConnectionProviderPair(
											"acme",
											ConnectionProviderBuilder.buildConnectionProvider( "acme" )
									)
							)
					)
					.build();

			new MetadataSources( serviceRegistry ).buildMetadata().buildSessionFactory().close();
		}
		finally {
			if ( serviceRegistry != null ) {
				try {
					StandardServiceRegistryBuilder.destroy( serviceRegistry );
				}
				catch (Exception ignore) {
				}
			}
		}
	}
