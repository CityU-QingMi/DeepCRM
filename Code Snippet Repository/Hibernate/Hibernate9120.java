	@Test
	public void testOneSelectNewAliasesList() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) throws Exception {
				return s.createCriteria( Student.class, "s" )
				.setProjection( Projections.property( "s.name" ).as( "name" ))
				.addOrder( Order.asc( "s.studentNumber" ) )
				.setResultTransformer( new AliasToBeanConstructorResultTransformer( getConstructor() ) );
			}
			private Constructor getConstructor() throws NoSuchMethodException {
				return StudentDTO.class.getConstructor( PersonName.class );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select new org.hibernate.test.querycache.StudentDTO(s.name) from Student s order by s.studentNumber" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				StudentDTO yogi = ( StudentDTO ) resultList.get( 0 );
				assertNull( yogi.getDescription() );
				assertEquals( yogiExpected.getName(), yogi.getName() );
				StudentDTO sherman = ( StudentDTO ) resultList.get( 1 );
				assertEquals( shermanExpected.getName(), sherman.getName() );
				assertNull( sherman.getDescription() );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, false );
	}
