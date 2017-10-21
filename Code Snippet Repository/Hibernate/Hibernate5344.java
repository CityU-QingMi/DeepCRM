	@Test
	@TestForIssue( jiraKey = "" )
	public void testBadUrl() throws Exception {
		DriverConnectionCreator connectionCreator = new DriverConnectionCreator(
				(Driver) Class.forName( "org.h2.Driver" ).newInstance(),
				new StandardServiceRegistryImpl(
						true,
						new BootstrapServiceRegistryImpl(),
						Collections.<StandardServiceInitiator>emptyList(),
						Collections.<ProvidedService>emptyList(),
						Collections.emptyMap()
				) {
					@Override
					@SuppressWarnings("unchecked")
					public <R extends Service> R getService(Class<R> serviceRole) {
						if ( JdbcServices.class.equals( serviceRole ) ) {
							// return a new, not fully initialized JdbcServicesImpl
							return (R) new JdbcServicesImpl();
						}
						return super.getService( serviceRole );
					}
				},
				"jdbc:h2:mem:test-bad-urls;nosuchparam=saywhat",
				new Properties(),
				false,
				null
		);

		try {
			Connection conn = connectionCreator.createConnection();
			conn.close();
			fail( "Expecting the bad Connection URL to cause an exception" );
		}
		catch (JDBCConnectionException expected) {
		}
	}
