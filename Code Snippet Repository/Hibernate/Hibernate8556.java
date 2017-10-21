	@Test
    @RequiresDialect(value = H2Dialect.class, comment = "")
	public void testUserProvidedConnection() throws Exception {
		ConnectionProvider dcp = ConnectionProviderBuilder.buildConnectionProvider();
		Session s = sessionFactory().withOptions().connection( dcp.getConnection() ).openSession();
		Transaction tx = s.beginTransaction();
		s.createQuery( "from Fo" ).list();
		tx.commit();
		Connection c = s.disconnect();
		assertTrue( c != null );
		s.reconnect( c );
		tx = s.beginTransaction();
		s.createQuery( "from Fo" ).list();
		tx.commit();
		c.close();
	}
