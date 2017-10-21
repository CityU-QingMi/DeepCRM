	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	@Test
	public void testTextTypeInSQLQuery() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		String description = buildLongString( 15000, 'a' );
		TextHolder holder = new TextHolder( description );
		s.persist( holder );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		String descriptionRead = ( String ) s.createSQLQuery( getDescriptionsSQL() )
				.uniqueResult();
		assertEquals( description, descriptionRead );
		s.delete( holder );
		t.commit();
		s.close();
	}
