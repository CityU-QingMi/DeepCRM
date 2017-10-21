	@Test
	@TestForIssue( jiraKey = "" )
	@FailureExpected( jiraKey = "")
	public void testElementMapIsEmptyCriteria() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			final HibernateCriteriaBuilder cb = (HibernateCriteriaBuilder) entityManager.getCriteriaBuilder();

			final CriteriaQuery<MapEntity> criteria = cb.createQuery( MapEntity.class );
			final Root<MapEntity> root = criteria.from( MapEntity.class);

			criteria.select( root )
					.where( cb.isMapEmpty( root.get( MapEntity_.localized ) ) );

			entityManager.createQuery( criteria ).getResultList();
		});
	}
