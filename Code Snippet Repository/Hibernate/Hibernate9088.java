	@Test
	public void testEntityWithAliasedJoinFetchedLazyOneToManySingleElementListHql() throws Exception {
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "from Student s left join fetch s.enrolments e order by s.studentNumber" );
			}
		};

		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				assertEquals( yogiExpected, resultList.get( 0 ) );
				assertEquals(
						yogiExpected.getPreferredCourse().getCourseCode(),
						( ( Student ) resultList.get( 0 ) ).getPreferredCourse().getCourseCode()
				);
				assertEquals( shermanExpected, resultList.get( 1 ) );
				assertNull( ( ( Student ) resultList.get( 1 ) ).getPreferredCourse() );
				if ( areDynamicNonLazyAssociationsChecked() ) {
					assertTrue( Hibernate.isInitialized( ( ( Student ) resultList.get( 0 ) ).getEnrolments() ) );
					assertEquals( yogiExpected.getEnrolments(), ( ( Student ) resultList.get( 0 ) ).getEnrolments() );
					assertTrue( Hibernate.isInitialized( ( ( Student ) resultList.get( 1 ) ).getEnrolments() ) );
					assertEquals( shermanExpected.getEnrolments(), ( ( ( Student ) resultList.get( 1 ) ).getEnrolments() ) );
				}
			}
		};

		runTest( hqlExecutor, null, checker, false);
	}
