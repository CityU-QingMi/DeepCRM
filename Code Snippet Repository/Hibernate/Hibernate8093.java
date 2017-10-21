	@Test
	public void testNoArgFcnAndColumnSameNameAsNoArgFunctionHQL() {
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
		List results = s.createQuery(
						"select str(current_date), currentDate from EntityWithNoArgFunctionAsColumn"
				).list();
		assertEquals( 2, results.size() );
		assertEquals( ( ( Object[] ) results.get( 0 ) )[ 0 ], ( ( Object[] ) results.get( 1 ) )[ 0 ] );
		assertTrue( ! ( ( Object[] ) results.get( 0 ) )[ 0 ].equals( ( ( Object[] ) results.get( 0 ) )[ 1 ] ) );
		assertTrue( ! ( ( Object[] ) results.get( 1 ) )[ 0 ].equals( ( ( Object[] ) results.get( 1 ) )[ 1 ] ) );
		assertTrue( ( ( Object[] ) results.get( 0 ) )[ 1 ].equals( e1.getCurrentDate() ) ||
				     ( ( Object[] ) results.get( 0 ) )[ 1 ].equals( e2.getCurrentDate() ) );
		assertTrue( ( ( Object[] ) results.get( 1 ) )[ 1 ].equals( e1.getCurrentDate() ) ||
				     ( ( Object[] ) results.get( 1 ) )[ 1 ].equals( e2.getCurrentDate() ) );
		assertFalse( ( ( Object[] ) results.get( 0 ) )[ 1 ].equals( ( ( Object[] ) results.get( 1 ) )[ 1 ] ) );
		t.commit();
		s.close();

		cleanup();
	}
