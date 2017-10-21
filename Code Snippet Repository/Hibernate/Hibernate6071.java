	@Test
	public void testUpdateNamedNativeQueryWithSqlComment() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			connectionProvider.clear();

			Query query = entityManager.createNamedQuery( "UpdateNamedNativeQuery" );
			query.setParameter( "title", GAME_TITLES[0] );
			query.setParameter( "id", 1L );
			int updateCount = query.executeUpdate();
			assertEquals( 1, updateCount );

			assertEquals(
				1,
				connectionProvider.getPreparedStatements().size()
			);

			assertNotNull(
				connectionProvider.getPreparedStatement(
					"/* INDEX (game idx_game_title)  */ update game set title = ? where id = ?"
				)
			);
		} );
	}
