	@Test
	public void testMapKeyList() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				return s.createCriteria( Student.class, "s" )
						.createAlias( "s.addresses", "a" )
				.setProjection( Projections.property( "a.addressType" ) );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select key(s.addresses) from Student s" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				assertTrue( resultList.contains( "home" ) );
				assertTrue( resultList.contains( "work" ) );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, false );
	}
