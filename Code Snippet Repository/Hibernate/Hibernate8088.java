	@Test
	public void testGetOneColumnSameNameAsArgFunctionCriteria() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		EntityWithArgFunctionAsColumn e = new EntityWithArgFunctionAsColumn();
		e.setLower( 3 );
		e.setUpper( " abc " );
		s.persist( e );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		e = ( EntityWithArgFunctionAsColumn ) s.createCriteria( EntityWithArgFunctionAsColumn.class ).uniqueResult();
		assertEquals( 3, e.getLower() );
		assertEquals( " abc ", e.getUpper() );
		t.commit();
		s.close();

		cleanup();
	}
