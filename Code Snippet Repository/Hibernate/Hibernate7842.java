	@Test
	public void testForIssue() {
		doInHibernate( this::sessionFactory, session -> {
			final org.hibernate.Filter enableFilter = session.enableFilter( "aliveOnly" );
			enableFilter.setParameter( "aliveTimestamp", Timestamp.valueOf( "9999-12-31 00:00:00" ) );
			enableFilter.setParameter( "deleted", true );
			enableFilter.validate();

			final Query query = session.createQuery( "select a.id from ArticleRevision as a " +
															 "left join a.articleTradings as t " +
															 "with ( (t.partyId = :p_0)  and  (t.classifier = :p_1) )" );
			query.setParameter( "p_0", 1L );
			query.setParameter( "p_1", "no_classification" );
			final List list = query.list();
			assertThat( list.size(), is( 1 ) );

		} );
	}
