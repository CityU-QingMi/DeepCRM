	@Test
	public void testSelectNamedNativeQueryWithSqlComment() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			connectionProvider.clear();

			TypedQuery<Game> query = entityManager.createNamedQuery( "SelectNamedNativeQuery", Game.class );
			query.setParameter( "title", GAME_TITLES[0] );
			List<Game> list = query.getResultList();
			assertEquals( 1, list.size() );

			assertEquals(
				1,
				connectionProvider.getPreparedStatements().size()
			);

			assertNotNull(
				connectionProvider.getPreparedStatement(
					"/* + INDEX (game idx_game_title)  */ select * from game g where title = ?"
				)
			);
		} );
	}
