	@Test
	public void testEntityWithLazyAssnUnique() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				// should use RootEntityTransformer by default
				return s.createCriteria( Student.class, "s" )
						.add( Restrictions.eq( "studentNumber", shermanExpected.getStudentNumber() ) );
				}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "from Student s where s.studentNumber = :studentNumber" )
						.setParameter( "studentNumber", shermanExpected.getStudentNumber() );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				assertTrue( results instanceof Student );
				assertEquals( shermanExpected, results );
				assertNotNull( ((Student) results).getEnrolments() );
				assertFalse( Hibernate.isInitialized( ((Student) results).getEnrolments() ) );
				assertNull( ((Student) results).getPreferredCourse() );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, true );
	}
