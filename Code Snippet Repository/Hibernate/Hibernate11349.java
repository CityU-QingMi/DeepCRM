	@Test
	public void testHikariCPConnectionProvider() throws Exception {
		JdbcServices jdbcServices = serviceRegistry().getService( JdbcServices.class );
		ConnectionProviderJdbcConnectionAccess connectionAccess = assertTyping(
				ConnectionProviderJdbcConnectionAccess.class,
				jdbcServices.getBootstrapJdbcConnectionAccess()
		);
		assertTyping( HikariCPConnectionProvider.class, connectionAccess.getConnectionProvider() );

		HikariCPConnectionProvider hikariCP = (HikariCPConnectionProvider) connectionAccess.getConnectionProvider();
		// For simplicity's sake, using the following in hibernate.properties:
		// hibernate.hikari.minimumPoolSize 2
		// hibernate.hikari.maximumPoolSize 2
		final List<Connection> conns = new ArrayList<Connection>();
		for ( int i = 0; i < 2; i++ ) {
			Connection conn = hikariCP.getConnection();
			assertNotNull( conn );
			assertFalse( conn.isClosed() );
			conns.add( conn );
		}

		try {
			hikariCP.getConnection();
			fail( "SQLException expected -- no more connections should have been available in the pool." );
		}
		catch (SQLException e) {
			// expected
			assertTrue( e.getMessage().contains( "Connection is not available, request timed out after" ) );
		}

		for ( Connection conn : conns ) {
			hikariCP.closeConnection( conn );
			assertTrue( conn.isClosed() );
		}

		releaseSessionFactory();

		try {
			hikariCP.getConnection();
			fail( "Exception expected -- the pool should have been shutdown." );
		}
		catch (Exception e) {
			// expected
			assertTrue( e.getMessage().contains( "has been closed" ) );
		}
	}
