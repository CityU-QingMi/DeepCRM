	@Test
	public void testEntityWithNonLazyManyToOneList() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				// should use RootEntityTransformer by default
				return s.createCriteria( CourseMeeting.class )
						.addOrder( Order.asc( "id.day" ) );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			protected Query getQuery(Session s) {
				return s.createQuery( "from CourseMeeting order by id.day" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				assertEquals( courseMeetingExpected1, resultList.get( 0 ) );
				assertEquals( courseMeetingExpected2, resultList.get( 1 ) );
				assertTrue( Hibernate.isInitialized( ((CourseMeeting) resultList.get( 0 )).getCourse() ) );
				assertTrue( Hibernate.isInitialized( ((CourseMeeting) resultList.get( 1 )).getCourse() ) );
				assertEquals( courseExpected, ((CourseMeeting) resultList.get( 0 )).getCourse() );
				assertEquals( courseExpected, ((CourseMeeting) resultList.get( 1 )).getCourse() );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, false );
	}
