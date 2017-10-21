	@Test
	@TestForIssue(jiraKey = "")
	public void testSelectFromHuman() throws Exception {
		doInHibernate( this::sessionFactory, session -> {
			session.enableFilter( "nameFilter" ).setParameter( "name", "unimportant" );

			List humans = session.createQuery( "SELECT h FROM Human h" ).list();

			assertThat( humans.size(), is( 1 ) );
			Human human = (Human) humans.get( 0 );
			assertThat( human.getName(), is( "unimportant" ) );
		} );
	}
