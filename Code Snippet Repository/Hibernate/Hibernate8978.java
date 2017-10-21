	@Test
	@TestForIssue( jiraKey = "" )
	public void testDuplicatedRequestedIds() {
		doInHibernate(
				this::sessionFactory, session -> {
					// ordered multiLoad
					List<SimpleEntity> list = session.byMultipleIds( SimpleEntity.class ).multiLoad( 1, 2, 3, 2, 2 );
					assertEquals( 5, list.size() );
					assertSame( list.get( 1 ), list.get( 3 ) );
					assertSame( list.get( 1 ), list.get( 4 ) );

					// un-ordered multiLoad
					list = session.byMultipleIds( SimpleEntity.class ).enableOrderedReturn( false ).multiLoad( 1, 2, 3, 2, 2 );
					assertEquals( 3, list.size() );
				}
		);
	}
