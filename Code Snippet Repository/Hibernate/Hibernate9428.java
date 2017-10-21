	@Test
	public void testSqlQueryAutoDiscovery() throws Exception {
		Session session = openSession();
		session.beginTransaction();
		User u = new User( "steve" );
		Group g = new Group( "developer" );
		Membership m = new Membership( u, g );
		session.save( u );
		session.save( g );
		session.save( m );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		List result = session.createSQLQuery( QUERY_STRING ).list();
		Object[] row = (Object[]) result.get( 0 );
		Assert.assertEquals( "steve", row[0] );
		Assert.assertEquals( "developer", row[1] );
		session.delete( m );
		session.delete( u );
		session.delete( g );
		session.getTransaction().commit();
		session.close();
	}
