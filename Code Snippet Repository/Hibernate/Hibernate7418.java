	@Test
	public void testSuppliedConnection() throws Throwable {
		prepare();

		Connection originalConnection = sessionFactory().getServiceRegistry().getService( ConnectionProvider.class ).getConnection();
		Session session = sessionFactory().withOptions().connection( originalConnection ).openSession();

		Silly silly = new Silly( "silly" );
		session.save( silly );

		// this will cause the connection manager to cycle through the aggressive release logic;
		// it should not release the connection since we explicitly suplied it ourselves.
		session.flush();
		assertTrue( session.isConnected() );

		session.delete( silly );
		session.flush();

		release( session );
		done();

		sessionFactory().getServiceRegistry().getService( ConnectionProvider.class ).closeConnection( originalConnection );
	}
