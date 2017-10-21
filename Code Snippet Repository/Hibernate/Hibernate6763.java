	@Test
	@TestForIssue(jiraKey = "")
	@RequiresDialectFeature(DialectChecks.UsesInputStreamToInsertBlob.class)
	public void testStreamResetBeforeParameterBinding() throws SQLException {
		final Session session = openSession();

		session.getTransaction().begin();
		LobHolder entity = new LobHolder(
				session.getLobHelper().createBlob( "blob".getBytes() ),
				session.getLobHelper().createClob( "clob" ), 0
		);
		session.persist( entity );
		session.getTransaction().commit();

		final Integer updatesLimit = 3;

		for ( int i = 1; i <= updatesLimit; ++i ) {
			session.getTransaction().begin();
			entity = (LobHolder) session.get( LobHolder.class, entity.getId() );
			entity.setCounter( i );
			entity = (LobHolder) session.merge( entity );
			session.getTransaction().commit();
		}

		session.getTransaction().begin();
		entity = (LobHolder) session.get( LobHolder.class, entity.getId() );
		entity.setBlobLocator( session.getLobHelper().createBlob( "updated blob".getBytes() ) );
		entity.setClobLocator( session.getLobHelper().createClob( "updated clob" ) );
		entity = (LobHolder) session.merge( entity );
		session.getTransaction().commit();

		session.clear();

		session.getTransaction().begin();
		checkState( "updated blob".getBytes(), "updated clob", updatesLimit, (LobHolder) session.get( LobHolder.class, entity.getId() ) );
		session.getTransaction().commit();

		session.close();
	}
