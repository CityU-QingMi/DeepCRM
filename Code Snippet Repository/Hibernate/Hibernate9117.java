	@Test
	public void testAliasToBeanDtoLiteralArgList() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				return s.createCriteria( Enrolment.class, "e" )
				.createAlias( "e.student", "st" )
				.createAlias( "e.course", "co" )
				.setProjection(
						Projections.projectionList()
								.add( Property.forName( "st.name" ).as( "studentName" ) )
								.add( Projections.sqlProjection(
										"'lame description' as courseDescription",
										new String[] { "courseDescription" },
										new Type[] { StandardBasicTypes.STRING }
								)
						)
				)
				.addOrder( Order.asc( "e.studentNumber" ) )
				.setResultTransformer( Transformers.aliasToBean( StudentDTO.class ) );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select st.name as studentName, 'lame description' as courseDescription from Enrolment e join e.student st join e.course co order by e.studentNumber" )
						.setResultTransformer( Transformers.aliasToBean( StudentDTO.class ) );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				StudentDTO dto = ( StudentDTO ) resultList.get( 0 );
				assertEquals( "lame description", dto.getDescription() );
				assertEquals( yogiExpected.getName(), dto.getName() );
				dto = ( StudentDTO ) resultList.get( 1 );
				assertEquals( "lame description", dto.getDescription() );
				assertEquals( shermanExpected.getName(), dto.getName() );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, false );
	}
