	@Test
	public void testGetMultiColumnSameNameAsNoArgFunctionHQL() throws Exception {
		SQLFunction function = sessionFactory().getSqlFunctionRegistry().findSQLFunction( "current_date" );
		if ( function == null || function.hasParenthesesIfNoArguments() ) {
			SkipLog.reportSkip( "current_date reuires ()", "tests noarg function that does not require ()" );
			return;
		}

		Session s = openSession();
		Transaction t = s.beginTransaction();
		EntityWithNoArgFunctionAsColumn e1 = new EntityWithNoArgFunctionAsColumn();
		e1.setCurrentDate( "blah blah blah" );
		EntityWithNoArgFunctionAsColumn e2 = new EntityWithNoArgFunctionAsColumn();
		e2.setCurrentDate( "yadda yadda yadda" );
		EntityWithFunctionAsColumnHolder holder1 = new EntityWithFunctionAsColumnHolder();
		holder1.getEntityWithNoArgFunctionAsColumns().add( e1 );
		EntityWithFunctionAsColumnHolder holder2 = new EntityWithFunctionAsColumnHolder();
		holder2.getEntityWithNoArgFunctionAsColumns().add( e2 );
		holder1.setNextHolder( holder2 );
		s.save( holder1 );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		holder1 = ( EntityWithFunctionAsColumnHolder ) s.createQuery(
				"from EntityWithFunctionAsColumnHolder h left join fetch h.entityWithNoArgFunctionAsColumns " +
						"left join fetch h.nextHolder left join fetch h.nextHolder.entityWithNoArgFunctionAsColumns " +
						"where h.nextHolder is not null" )
				.uniqueResult();
		assertTrue( Hibernate.isInitialized( holder1.getEntityWithNoArgFunctionAsColumns() ) );
		assertTrue( Hibernate.isInitialized( holder1.getNextHolder() ) );
		assertTrue( Hibernate.isInitialized( holder1.getNextHolder().getEntityWithNoArgFunctionAsColumns() ) );
		assertEquals( 1, holder1.getEntityWithNoArgFunctionAsColumns().size() );
		t.commit();
		s.close();

		e1 = ( EntityWithNoArgFunctionAsColumn ) holder1.getEntityWithNoArgFunctionAsColumns().iterator().next();
		assertEquals( "blah blah blah", e1.getCurrentDate() );
		assertEquals( 1, holder1.getNextHolder().getEntityWithNoArgFunctionAsColumns().size() );
		e2 = ( EntityWithNoArgFunctionAsColumn ) ( holder1.getNextHolder() ).getEntityWithNoArgFunctionAsColumns().iterator().next();
		assertEquals( "yadda yadda yadda", e2.getCurrentDate() );

		cleanup();
	}
