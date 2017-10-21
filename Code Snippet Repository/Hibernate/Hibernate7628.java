	@Test
	@TestForIssue(jiraKey = "")
	public void testMaxResultsSqlServerWithCaseSensitiveCollation() throws Exception {
		doInHibernate( this::sessionFactory, session -> {
			for ( int i = 1; i <= 20; i++ ) {
				session.persist( new CustomProduct( i, "Kit" + i ) );
			}
			session.flush();
			session.clear();

			List list = session.createQuery( "from CustomProduct where description like 'Kit%'" )
					.setFirstResult( 2 )
					.setMaxResults( 2 )
					.list();
			assertEquals( 2, list.size() );
		} );
	}
