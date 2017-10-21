	@Test
	public void testMultiSelectNewMapUsingAliasesList() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				return s.createCriteria( Student.class, "s" )
				.setProjection(
						Projections.projectionList()
								.add( Property.forName( "s.studentNumber" ).as( "sNumber" ) )
								.add( Property.forName( "s.name" ).as( "sName" ) )
				)
				.addOrder( Order.asc( "s.studentNumber" ) )
				.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select new map(s.studentNumber as sNumber, s.name as sName) from Student s order by s.studentNumber" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				Map yogiMap = ( Map ) resultList.get( 0 );
				assertEquals( yogiExpected.getStudentNumber(), yogiMap.get( "sNumber" ) );
				assertEquals( yogiExpected.getName(), yogiMap.get( "sName" ) );
				Map shermanMap = ( Map ) resultList.get( 1 );
				assertEquals( shermanExpected.getStudentNumber(), shermanMap.get( "sNumber" ) );
				assertEquals( shermanExpected.getName(), shermanMap.get( "sName" ) );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, false );
	}
