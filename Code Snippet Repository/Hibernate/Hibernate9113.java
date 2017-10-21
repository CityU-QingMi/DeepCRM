	@Test
	public void testAliasToEntityMapNoProjectionMultiAndNullList() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			@Override
            protected Criteria getCriteria(Session s) {
				return s.createCriteria( Student.class, "s" )
						.createAlias( "s.preferredCourse", "p", CriteriaSpecification.LEFT_JOIN )
						.createAlias( "s.addresses", "a", CriteriaSpecification.LEFT_JOIN )
								.setResultTransformer( CriteriaSpecification.ALIAS_TO_ENTITY_MAP )
						.addOrder( Order.asc( "s.studentNumber" ) );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			@Override
            public Query getQuery(Session s) {
				return s.createQuery( "from Student s left join s.preferredCourse p left join s.addresses a order by s.studentNumber" )
						.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 3, resultList.size() );
				Map yogiMap1 = ( Map ) resultList.get( 0 );
				assertEquals( 3, yogiMap1.size() );
				Map yogiMap2 = ( Map ) resultList.get( 1 );
				assertEquals( 3, yogiMap2.size() );
				Map shermanMap = ( Map ) resultList.get( 2 );
				assertEquals( 3, shermanMap.size() );
				assertEquals( yogiExpected, yogiMap1.get( "s" ) );
				assertEquals( courseExpected, yogiMap1.get( "p" ) );
				Address yogiAddress1 = ( Address ) yogiMap1.get( "a" );
				assertEquals( yogiExpected.getAddresses().get( yogiAddress1.getAddressType() ),
						yogiMap1.get( "a" ));
				assertEquals( yogiExpected, yogiMap2.get( "s" ) );
				assertEquals( courseExpected, yogiMap2.get( "p" ) );
				Address yogiAddress2 = ( Address ) yogiMap2.get( "a" );
				assertEquals( yogiExpected.getAddresses().get( yogiAddress2.getAddressType() ),
						yogiMap2.get( "a" ));
				assertSame( yogiMap1.get( "s" ), yogiMap2.get( "s" ) );
				assertSame( yogiMap1.get( "p" ), yogiMap2.get( "p" ) );
				assertFalse( yogiAddress1.getAddressType().equals( yogiAddress2.getAddressType() ) );
				assertEquals( shermanExpected, shermanMap.get( "s" ) );
				assertEquals( shermanExpected.getPreferredCourse(), shermanMap.get( "p" ) );
				assertNull( shermanMap.get( "a" ) );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, false );
	}
