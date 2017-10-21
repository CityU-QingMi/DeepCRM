	@Test
	public void testOrdinalParameters() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		s.createQuery( "from Animal a where a.description = ? and a.bodyWeight = ?" )
				.setString( 0, "something" )
				.setFloat( 1, 123f )
				.list();
		s.createQuery( "from Animal a where a.bodyWeight in (?, ?)" )
				.setFloat( 0, 999f )
				.setFloat( 1, 123f )
				.list();
		t.commit();
		s.close();
	}
