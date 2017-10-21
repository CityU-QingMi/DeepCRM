	@Test
	public void testMultiSelectUsingImplicitJoinWithFetchJoinListHql() throws Exception {
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select s as s, s.preferredCourse as pc from Student s left join fetch s.enrolments" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				assertTrue( results instanceof Object[] );
				Object[] yogiObjects = ( Object[] ) results;
				assertEquals( 2, yogiObjects.length );
				assertEquals( yogiExpected, yogiObjects[ 0 ] );
				assertEquals(
						yogiExpected.getPreferredCourse().getCourseCode(),
						( ( Course ) yogiObjects[ 1 ] ).getCourseCode()
				);
				if ( areDynamicNonLazyAssociationsChecked() ) {
					assertEquals( yogiExpected.getPreferredCourse(), yogiObjects[ 1 ] );
					assertTrue( Hibernate.isInitialized( ( ( Student ) yogiObjects[ 0 ] ).getEnrolments() ) );
					assertEquals( yogiExpected.getEnrolments(), ( ( Student ) yogiObjects[ 0 ] ).getEnrolments() );
				}
			}
		};
		runTest( hqlExecutor, null, checker, true );
	}
