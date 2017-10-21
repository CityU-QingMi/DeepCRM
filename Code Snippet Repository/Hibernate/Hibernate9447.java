	@Test
	public void testParameterHandling() throws HibernateException, SQLException {
		Session s = openSession();

		Query namedQuery = s.getNamedQuery( "paramhandling" );
		namedQuery.setLong( 0, 10 );
		namedQuery.setLong( 1, 20 );
		List list = namedQuery.list();
		Object[] o = ( Object[] ) list.get( 0 );
		assertEquals( o[0], Long.valueOf( 10 ) );
		assertEquals( o[1], Long.valueOf( 20 ) );

		namedQuery = s.getNamedQuery( "paramhandling_mixed" );
		namedQuery.setLong( 0, 10 );
		namedQuery.setLong( "second", 20 );
		list = namedQuery.list();
		o = ( Object[] ) list.get( 0 );
		assertEquals( o[0], Long.valueOf( 10 ) );
		assertEquals( o[1], Long.valueOf( 20 ) );
		s.close();
	}
