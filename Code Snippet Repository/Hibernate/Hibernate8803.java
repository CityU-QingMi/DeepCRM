	@Test(expected = ServiceException.class)
	public void testInvalidConnectionProvider() {
		ServiceRegistryImplementor serviceRegistry = null;
		try {
			serviceRegistry	= (ServiceRegistryImplementor) new StandardServiceRegistryBuilder()
					.applySetting( Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA  )
					.applySetting( Environment.MULTI_TENANT_CONNECTION_PROVIDER, "class.not.present.in.classpath" )
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
