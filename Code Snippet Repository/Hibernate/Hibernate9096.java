	@Test
	public void testEntityWithJoinedLazyOneToManyMultiAndNullListHql() throws Exception {
		HqlExecutor hqlExecutorUnaliased = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "from Student s left join s.addresses order by s.studentNumber" );
			}
		};
		HqlExecutor hqlExecutorAliased = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "from Student s left join s.addresses a order by s.studentNumber" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 3, resultList.size() );
				assertTrue( resultList.get( 0 ) instanceof Object[] );
				Object[] yogiObjects1 = ( Object[] ) resultList.get( 0 );
				assertEquals( yogiExpected, yogiObjects1[ 0 ] );
				Address address1 = ( Address ) yogiObjects1[ 1 ];
				assertEquals( yogiExpected.getAddresses().get( address1.getAddressType() ), address1 );
				Object[] yogiObjects2 = ( Object[] ) resultList.get( 1 );
				assertSame( yogiObjects1[ 0 ], yogiObjects2[ 0 ] );
				Address address2 = ( Address ) yogiObjects2[ 1 ];
				assertEquals( yogiExpected.getAddresses().get( address2.getAddressType() ), address2 );
				assertFalse( address1.getAddressType().equals( address2.getAddressType() ) );
				Object[] shermanObjects = ( Object[] ) resultList.get( 2 );
				assertEquals( shermanExpected, shermanObjects[ 0 ] );
				assertNull( shermanObjects[ 1 ] );
			}
		};
		runTest( hqlExecutorUnaliased, null, checker, false );
		runTest( hqlExecutorAliased, null, checker, false );
	}
