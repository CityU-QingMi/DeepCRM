	@Test
	public void testScalarStoredProcedure() throws HibernateException, SQLException {
		Session s = openSession();
		Query namedQuery = s.getNamedQuery( "simpleScalar" );
		namedQuery.setLong( "number", 43 );
		List list = namedQuery.list();
		Object o[] = ( Object[] ) list.get( 0 );
		assertEquals( o[0], "getAll" );
		assertEquals( o[1], Long.valueOf( 43 ) );
		s.close();
	}
