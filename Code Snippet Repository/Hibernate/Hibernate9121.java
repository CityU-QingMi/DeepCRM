	@Test
	public void testMultiSelectNewList() throws Exception{
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) throws Exception {
				return s.createCriteria( Student.class, "s" )
				.setProjection(
						Projections.projectionList()
								.add( Property.forName( "s.studentNumber" ).as( "studentNumber" ))
								.add( Property.forName( "s.name" ).as( "name" ))
				)
				.addOrder( Order.asc( "s.studentNumber" ) )
				.setResultTransformer( new AliasToBeanConstructorResultTransformer( getConstructor() ) );
			}
			private Constructor getConstructor() throws NoSuchMethodException {
				return  Student.class.getConstructor( long.class, PersonName.class );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select new Student(s.studentNumber, s.name) from Student s order by s.studentNumber" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				Student yogi = ( Student ) resultList.get( 0 );
				assertEquals( yogiExpected.getStudentNumber(), yogi.getStudentNumber() );
				assertEquals( yogiExpected.getName(), yogi.getName() );
				Student sherman = ( Student ) resultList.get( 1 );
				assertEquals( shermanExpected.getStudentNumber(), sherman.getStudentNumber() );
				assertEquals( shermanExpected.getName(), sherman.getName() );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, false );
	}
