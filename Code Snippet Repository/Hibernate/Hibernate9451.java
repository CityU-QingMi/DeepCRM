	@Test
	public void testBadInsertionFails() {
		Session session = openSession();
		session.beginTransaction();
		Organization org = new Organization( "hola!" );
		try {
			session.save( org );
			session.delete( org );
			fail( "expecting bad custom insert statement to fail" );
		}
		catch( JDBCException e ) {
			// expected failure
		}

		session.getTransaction().rollback();
		session.close();
	}
