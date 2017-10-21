	@Test
	public void testSessionClosedProtections() throws Throwable {
		prepare();
		Session s = getSessionUnderTest();
		release( s );
		done();
		assertFalse( s.isOpen() );
		assertFalse( s.isConnected() );
		assertNotNull( s.getStatistics() );
		assertNotNull( s.toString() );

		try {
			s.createQuery( "from Silly" ).list();
			fail( "allowed to create query on closed session" );
		}
		catch( Throwable ignore ) {
		}

		try {
			s.getTransaction();
			fail( "allowed to access transaction on closed session" );
		}
		catch( Throwable ignore ) {
		}

		try {
			s.close();
			fail( "allowed to close already closed session" );
		}
		catch( Throwable ignore ) {
		}

		try {
			s.isDirty();
			fail( "allowed to check dirtiness of closed session" );
		}
		catch( Throwable ignore ) {
		}
	}
