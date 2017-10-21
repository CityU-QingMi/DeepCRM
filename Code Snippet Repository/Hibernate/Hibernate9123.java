	@Test
	public void testMultiSelectNewListList() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				return s.createCriteria( Student.class, "s" )
				.setProjection(
						Projections.projectionList()
								.add( Property.forName( "s.studentNumber" ).as( "studentNumber" ))
								.add( Property.forName( "s.name" ).as( "name" ) )
				)
				.addOrder( Order.asc( "s.studentNumber" ) )
				.setResultTransformer( Transformers.TO_LIST );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select new list(s.studentNumber, s.name) from Student s order by s.studentNumber" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				List yogiList = ( List ) resultList.get( 0 );
				assertEquals( yogiExpected.getStudentNumber(), yogiList.get( 0 ) );
				assertEquals( yogiExpected.getName(), yogiList.get( 1 ) );
				List shermanList = ( List ) resultList.get( 1 );
				assertEquals( shermanExpected.getStudentNumber(), shermanList.get( 0 ) );
				assertEquals( shermanExpected.getName(), shermanList.get( 1 ) );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, false );
	}
