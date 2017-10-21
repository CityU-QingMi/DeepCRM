	@Test
	public void test_sql_jpa_composite_key_entity_associations_named_query_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::sql-jpa-composite-key-entity-associations_named-query-example[]
			List<Object[]> tuples = entityManager.createNamedQuery(
				"find_all_spaceships" )
			.getResultList();

			for(Object[] tuple : tuples) {
				SpaceShip spaceShip = (SpaceShip) tuple[0];
				Number surface = (Number) tuple[1];
				Number volume = (Number) tuple[2];
			}
			//end::sql-jpa-composite-key-entity-associations_named-query-example[]
			assertEquals(1, tuples.size());
		});
	}
