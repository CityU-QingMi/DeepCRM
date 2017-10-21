	@Test
	public void testEntityWithNonLazyOneToManyUnique() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				// should use RootEntityTransformer by default
				return s.createCriteria( Course.class );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "from Course" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				assertTrue( results instanceof Course );
				assertEquals( courseExpected, results );
				assertTrue( Hibernate.isInitialized( courseExpected.getCourseMeetings() ) );
				assertEquals( courseExpected.getCourseMeetings(), courseExpected.getCourseMeetings() );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, true );
	}
