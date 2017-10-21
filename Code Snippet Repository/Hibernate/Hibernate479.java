	@Test
	public void testC3P0isDefaultWhenThereIsC3P0Properties() {
		JdbcServices jdbcServices = serviceRegistry().getService( JdbcServices.class );
		ConnectionProviderJdbcConnectionAccess connectionAccess =
			assertTyping(
				ConnectionProviderJdbcConnectionAccess.class,
				jdbcServices.getBootstrapJdbcConnectionAccess()
			);
		assertTrue( connectionAccess.getConnectionProvider() instanceof C3P0ConnectionProvider );
	}
