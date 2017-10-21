	@Test
	@RequiresDialect(H2Dialect.class)
	public void testUpdateNamedNativeQueryWithQueryHintUsingIndex() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			connectionProvider.clear();

			Query query = entityManager.createNamedQuery( "UpdateNamedNativeQuery" );
			query.setParameter( "title", GAME_TITLES[0] );
			query.setParameter( "id", 1L );
			query.unwrap( org.hibernate.query.Query.class ).addQueryHint( "INDEX (game idx_game_id)" );
			int updateCount = query.executeUpdate();
			assertEquals( 1, updateCount );

			assertEquals(
				1,
				connectionProvider.getPreparedStatements().size()
			);

			assertNotNull(
				connectionProvider.getPreparedStatement(
					"update game set title = ? where id = ?"
				)
			);
		} );
	}
