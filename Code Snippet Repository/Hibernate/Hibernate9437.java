	@Test
	public void testWithoutComma() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Date now = entityManager.createQuery(
				"select FUNCTION('now') " +
				"from Event " +
				"where id = :id", Date.class)
			.setParameter( "id", 1L )
			.getSingleResult();
			log.infof( "Current time: {}", now );
		} );
	}
