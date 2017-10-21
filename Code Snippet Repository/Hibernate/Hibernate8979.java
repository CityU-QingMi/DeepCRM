	@Test
	@TestForIssue( jiraKey = "" )
	public void testNonExistentIdRequest() {
		doInHibernate(
				this::sessionFactory, session -> {
					// ordered multiLoad
					List<SimpleEntity> list = session.byMultipleIds( SimpleEntity.class ).multiLoad( 1, 699, 2 );
					assertEquals( 3, list.size() );
					assertNull( list.get( 1 ) );

					// un-ordered multiLoad
					list = session.byMultipleIds( SimpleEntity.class ).enableOrderedReturn( false ).multiLoad( 1, 699, 2 );
					assertEquals( 2, list.size() );
				}
		);
	}
