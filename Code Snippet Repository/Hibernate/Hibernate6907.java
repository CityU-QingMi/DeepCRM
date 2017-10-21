	@Test
	public void testHHH4851() throws Exception {
		Session session = openSession();
		Transaction trx = session.beginTransaction();
		Owner org = new Owner();
		org.setName( "root" );
		session.saveOrUpdate( org );

		ManagedDevice lTerminal = new ManagedDevice();
		lTerminal.setName( "test" );
		lTerminal.setOwner( org );
		session.saveOrUpdate( lTerminal );

		Device terminal = new Device();
		terminal.setTag( "test" );
		terminal.setOwner( org );
		try {
			session.saveOrUpdate( terminal );
		}
		catch ( PropertyValueException e ) {
			fail( "not-null checking should not be raised: " + e.getMessage() );
		}
		trx.commit();
		session.close();
	}
