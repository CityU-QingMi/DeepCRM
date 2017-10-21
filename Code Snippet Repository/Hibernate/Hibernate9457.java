	@Test
	public void testMixAndMatchEntityScalar() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Speech speech = new Speech();
		speech.setLength( new Double( 23d ) );
		speech.setName( "Mine" );
		s.persist( speech );
		s.flush();
		s.clear();

		List l = s.createSQLQuery( "select name, id, flength, name as scalarName from Speech" )
				.setResultSetMapping( "speech" )
				.list();
		assertEquals( l.size(), 1 );

		t.rollback();
		s.close();
	}
