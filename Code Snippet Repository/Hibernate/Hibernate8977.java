	@Test
	@TestForIssue( jiraKey = "" )
	public void testUnflushedDeleteAndThenMultiLoad() {
		doInHibernate(
				this::sessionFactory, session -> {
					// delete one of them (but do not flush)...
					session.delete( session.load( SimpleEntity.class, 5 ) );

					// as a baseline, assert based on how load() handles it
					SimpleEntity s5 = session.load( SimpleEntity.class, 5 );
					assertNotNull( s5 );

					// and then, assert how get() handles it
					s5 = session.get( SimpleEntity.class, 5 );
					assertNull( s5 );

					// finally assert how multiLoad handles it
					List<SimpleEntity> list = session.byMultipleIds( SimpleEntity.class ).multiLoad( ids(56) );
					assertEquals( 56, list.size() );
				}
		);
	}
