	@Test
	public void testMultiProjectionListThenApplyAliasToBean() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				return s.createCriteria( Enrolment.class, "e" )
				.createAlias( "e.student", "st" )
				.createAlias( "e.course", "co" )
				.setProjection(
						Projections.projectionList()
								.add( Property.forName( "st.name" ) )
								.add( Property.forName( "co.description" ) )
				)
				.addOrder( Order.asc( "e.studentNumber" ) );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select st.name as studentName, co.description as courseDescription from Enrolment e join e.student st join e.course co order by e.studentNumber" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				ResultTransformer transformer = Transformers.aliasToBean( StudentDTO.class );
				String[] aliases = new String[] { "studentName", "courseDescription" };
				for ( int i = 0 ; i < resultList.size(); i++ ) {
					resultList.set(
							i,
							transformer.transformTuple( ( Object[] ) resultList.get( i ), aliases )
					);
				}

				assertEquals( 2, resultList.size() );
				StudentDTO dto = ( StudentDTO ) resultList.get( 0 );
				assertEquals( courseExpected.getDescription(), dto.getDescription() );
				assertEquals( yogiExpected.getName(), dto.getName() );
				dto = ( StudentDTO ) resultList.get( 1 );
				assertEquals( courseExpected.getDescription(), dto.getDescription() );
				assertEquals( shermanExpected.getName(), dto.getName() );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, false );
	}
