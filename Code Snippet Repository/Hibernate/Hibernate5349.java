	@Test
	public void testLegacyLimitHandlerWithOffset() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			List<SimpleEntity> results = entityManager.createQuery( "FROM SimpleEntity", SimpleEntity.class )
					.setFirstResult( 2 )
					.setMaxResults( 2 )
					.getResultList();
			assertEquals( Arrays.asList( 2, 3 ), results.stream().map( SimpleEntity::getId ).collect( Collectors.toList() ) );
		} );
	}
