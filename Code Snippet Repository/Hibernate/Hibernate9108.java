	@Test
	public void testMultiEntityProjectionUnique() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				// should use PassThroughTransformer by default
				return s.createCriteria( Enrolment.class )
						.setProjection(
								Projections.projectionList()
										.add( Property.forName( "student" ) )
										.add( Property.forName( "semester" ) )
										.add( Property.forName( "year" ) )
										.add( Property.forName( "course" ) )
						)
						.add( Restrictions.eq( "studentNumber", Long.valueOf( shermanEnrolmentExpected.getStudentNumber() ) ) );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery(
						"select e.student, e.semester, e.year, e.course from Enrolment e  where e.studentNumber = :studentNumber" )
						.setParameter( "studentNumber", shermanEnrolmentExpected.getStudentNumber() );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				assertTrue( results instanceof Object[] );
				Object shermanObjects[] = ( Object [] ) results;
				assertEquals( 4, shermanObjects.length );
				assertNotNull( shermanObjects[ 0 ] );
				assertTrue( shermanObjects[ 0 ] instanceof Student );
				// TODO: following is initialized for hql and uninitialied for criteria; why?
				//assertFalse( Hibernate.isInitialized( shermanObjects[ 0 ] ) );
				assertEquals( shermanEnrolmentExpected.getSemester(), ( (Short) shermanObjects[ 1 ] ).shortValue() );
				assertEquals( shermanEnrolmentExpected.getYear(), ( (Short) shermanObjects[ 2 ] ).shortValue() );
				assertTrue( ! ( shermanObjects[ 3 ] instanceof HibernateProxy ) );
				assertTrue( shermanObjects[ 3 ] instanceof Course );
				assertEquals( courseExpected, shermanObjects[ 3 ] );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, true );
	}
