	@Test
	public void testGetMultiColumnSameNameAsArgFunctionHQL() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		EntityWithArgFunctionAsColumn e1 = new EntityWithArgFunctionAsColumn();
		e1.setLower( 3 );
		e1.setUpper( " abc " );
		EntityWithArgFunctionAsColumn e2 = new EntityWithArgFunctionAsColumn();
		e2.setLower( 999 );
		e2.setUpper( " xyz " );
		EntityWithFunctionAsColumnHolder holder1 = new EntityWithFunctionAsColumnHolder();
		holder1.getEntityWithArgFunctionAsColumns().add( e1 );
		EntityWithFunctionAsColumnHolder holder2 = new EntityWithFunctionAsColumnHolder();
		holder2.getEntityWithArgFunctionAsColumns().add( e2 );
		holder1.setNextHolder( holder2 );
		s.save( holder1 );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		holder1 = ( EntityWithFunctionAsColumnHolder ) s.createQuery(
				"from EntityWithFunctionAsColumnHolder h left join fetch h.entityWithArgFunctionAsColumns " +
						"left join fetch h.nextHolder left join fetch h.nextHolder.entityWithArgFunctionAsColumns " +
						"where h.nextHolder is not null" )
				.uniqueResult();
		assertTrue( Hibernate.isInitialized( holder1.getEntityWithArgFunctionAsColumns() ) );
		assertTrue( Hibernate.isInitialized( holder1.getNextHolder() ) );
		assertTrue( Hibernate.isInitialized( holder1.getNextHolder().getEntityWithArgFunctionAsColumns() ) );
		assertEquals( 1, holder1.getEntityWithArgFunctionAsColumns().size() );
		e1 = ( EntityWithArgFunctionAsColumn ) holder1.getEntityWithArgFunctionAsColumns().iterator().next();
		assertEquals( 3, e1.getLower() );
		assertEquals( " abc ", e1.getUpper() );
		assertEquals( 1, holder1.getNextHolder().getEntityWithArgFunctionAsColumns().size() );
		e2 = ( EntityWithArgFunctionAsColumn ) ( holder1.getNextHolder() ).getEntityWithArgFunctionAsColumns().iterator().next();
		assertEquals( 999, e2.getLower() );
		assertEquals( " xyz ", e2.getUpper() );
		t.commit();
		s.close();

		cleanup();
	}
