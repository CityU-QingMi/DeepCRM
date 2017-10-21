	@Test
	public void testSelectNamedQueryWithSqlComment() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			connectionProvider.clear();

			TypedQuery<Game> query = entityManager.createNamedQuery( "SelectNamedQuery", Game.class );
			query.setParameter( "title", GAME_TITLES[0] );
			List<Game> list = query.getResultList();
			assertEquals( 1, list.size() );

			assertEquals(
				1,
				connectionProvider.getPreparedStatements().size()
			);

			assertNotNull(
				connectionProvider.getPreparedStatement(
					"/* INDEX (game idx_game_title) */ select namedquery0_.id as id1_0_, namedquery0_.title as title2_0_ from game namedquery0_ where namedquery0_.title=?"
				)
			);
		} );
	}
