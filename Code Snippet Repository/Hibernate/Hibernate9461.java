	@Test
	@RequiresDialect(MySQL5Dialect.class)
	public void testEscapeColonInSQL() throws QueryException {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		SQLQuery query = s.createSQLQuery( "SELECT @row \\:= 1" );
		List list = query.list();
		assertTrue( list.get( 0 ).toString().equals( "1" ) );
		t.commit();
		s.close();
	}
