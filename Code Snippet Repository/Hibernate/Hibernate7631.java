	@Test
	public void testPaginationQuery() {
		// prepare some test data
		doInJPA( this::entityManagerFactory, entityManager -> {
			for ( int i = 1; i <= 20; ++i ) {
				final SimpleEntity entity = new SimpleEntity( i, "Entity" + i );
				entityManager.persist( entity );
			}
		} );

		// This would fail with "index 2 out of range" within TopLimitHandler
		// The fix addresses this problem which only occurs when using SQLServerDialect explicitly.
		doInJPA( this::entityManagerFactory, entityManager -> {
			List<SimpleEntity> results = entityManager
					.createQuery( "SELECT o FROM SimpleEntity o WHERE o.id >= :firstId ORDER BY o.id", SimpleEntity.class )
					.setParameter( "firstId", 10 )
					.setMaxResults( 5 )
					.getResultList();
			// verify that the paginated query returned the right ids.
			final List<Integer> ids = results.stream().map( SimpleEntity::getId ).collect( Collectors.toList() );
			assertEquals( Arrays.asList( 10, 11, 12, 13, 14 ), ids );
		} );
	}
