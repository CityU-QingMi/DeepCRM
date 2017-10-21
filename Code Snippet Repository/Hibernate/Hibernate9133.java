	@Test
	public void testMapEntryList() throws Exception {
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select entry(s.addresses) from Student s" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				Iterator it=resultList.iterator();
				assertTrue( resultList.get( 0 ) instanceof Map.Entry );
				Map.Entry entry = ( Map.Entry ) it.next();
				if ( "home".equals( entry.getKey() ) ) {
					assertTrue( yogiExpected.getAddresses().get( "home" ).equals( entry.getValue() ) );
					entry = ( Map.Entry ) it.next();
					assertTrue( yogiExpected.getAddresses().get( "work" ).equals( entry.getValue() ) );
				}
				else {
					assertTrue( "work".equals( entry.getKey() ) );
					assertTrue( yogiExpected.getAddresses().get( "work" ).equals( entry.getValue() ) );
					entry = ( Map.Entry ) it.next();
					assertTrue( yogiExpected.getAddresses().get( "home" ).equals( entry.getValue() ) );
				}
			}
		};
		runTest( hqlExecutor, null, checker, false );
	}
