	@Test
	public void testAliasToEntityMapNoProjectionNullAndNonNullAliasList() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			@Override
            protected Criteria getCriteria(Session s) {
				return s.createCriteria( Student.class, "s" )
						.createAlias( "s.addresses", "a", CriteriaSpecification.LEFT_JOIN )
								.setResultTransformer( CriteriaSpecification.ALIAS_TO_ENTITY_MAP )
						.createCriteria( "s.preferredCourse", CriteriaSpecification.INNER_JOIN )
						.addOrder( Order.asc( "s.studentNumber") );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			@Override
            public Query getQuery(Session s) {
				return s.createQuery( "from Student s left join s.addresses a left join s.preferredCourse order by s.studentNumber" )
						.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				Map yogiMap1 = ( Map ) resultList.get( 0 );
				assertEquals( 2, yogiMap1.size() );
				Map yogiMap2 = ( Map ) resultList.get( 1 );
				assertEquals( 2, yogiMap2.size() );
				assertEquals( yogiExpected, yogiMap1.get( "s" ) );
				Address yogiAddress1 = ( Address ) yogiMap1.get( "a" );
				assertEquals( yogiExpected.getAddresses().get( yogiAddress1.getAddressType() ),
						yogiMap1.get( "a" ));
				assertEquals( yogiExpected, yogiMap2.get( "s" ) );
				Address yogiAddress2 = ( Address ) yogiMap2.get( "a" );
				assertEquals( yogiExpected.getAddresses().get( yogiAddress2.getAddressType() ),
						yogiMap2.get( "a" ));
				assertSame( yogiMap1.get( "s" ), yogiMap2.get( "s" ) );
				assertFalse( yogiAddress1.getAddressType().equals( yogiAddress2.getAddressType() ) );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, false );
	}
