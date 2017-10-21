	@Test
	public void testDistinctProjection() {

        doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-distinct-projection-query-example[]
            List<String> lastNames = entityManager.createQuery(
				"select distinct p.lastName " +
				"from Person p", String.class)
			.getResultList();
			//end::hql-distinct-projection-query-example[]

			assertTrue(
				lastNames.size() == 2 &&
				lastNames.contains( "King" ) &&
				lastNames.contains( "Mihalcea" )
			);
		});
	}
