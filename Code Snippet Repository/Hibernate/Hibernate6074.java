	@Test
	@RequiresDialect(MySQLDialect.class)
	@RequiresDialect(H2Dialect.class)
	public void testSelectNamedNativeQueryWithQueryHintUsingIndex() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			connectionProvider.clear();

			Query query = entityManager.createNamedQuery( "SelectNamedQuery" );
			query.setParameter( "title", GAME_TITLES[0] );
			query.unwrap( org.hibernate.query.Query.class ).addQueryHint( "idx_game_id" );
			List<Game> list = query.getResultList();
			assertEquals( 1, list.size() );

			assertEquals(
				1,
				connectionProvider.getPreparedStatements().size()
			);

			assertNotNull(
				connectionProvider.getPreparedStatement(
					"select namedquery0_.id as id1_0_, namedquery0_.title as title2_0_ from game namedquery0_  USE INDEX (idx_game_id) where namedquery0_.title=?"
				)
			);
		} );
	}
