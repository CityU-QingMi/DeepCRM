	@Test
	@TestForIssue( jiraKey = "" )
	public void testElementMapSizeCriteria() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			final HibernateCriteriaBuilder cb = (HibernateCriteriaBuilder) entityManager.getCriteriaBuilder();

			final CriteriaQuery<MapEntity> criteria = cb.createQuery( MapEntity.class );
			final Root<MapEntity> root = criteria.from( MapEntity.class);

			criteria.select( root )
					.where( cb.gt( cb.mapSize( root.get( MapEntity_.localized ) ), 1 ) );

			entityManager.createQuery( criteria ).getResultList();
		});
	}
