	@Test
	public void testAliasToBeanDtoOneArgList() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				return s.createCriteria( Enrolment.class, "e" )
				.createAlias( "e.student", "st" )
				.createAlias( "e.course", "co" )
				.setProjection( Projections.property( "st.name" ).as( "studentName" ) )
				.addOrder( Order.asc( "st.studentNumber" ) )
				.setResultTransformer( Transformers.aliasToBean( StudentDTO.class ) );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select st.name as studentName from Student st order by st.studentNumber" )
						.setResultTransformer( Transformers.aliasToBean( StudentDTO.class ) );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				StudentDTO dto = ( StudentDTO ) resultList.get( 0 );
				assertNull( dto.getDescription() );
				assertEquals( yogiExpected.getName(), dto.getName() );
				dto = ( StudentDTO ) resultList.get( 1 );
				assertNull( dto.getDescription() );
				assertEquals( shermanExpected.getName(), dto.getName() );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, false );
	}
