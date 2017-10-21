	@Test
	public void test_sql_hibernate_composite_key_entity_associations_named_query_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::sql-hibernate-composite-key-entity-associations_named-query-example[]
			List<Object[]> tuples = session.getNamedQuery(
				"find_all_spaceships" )
			.list();

			for(Object[] tuple : tuples) {
				SpaceShip spaceShip = (SpaceShip) tuple[0];
				Number surface = (Number) tuple[1];
				Number volume = (Number) tuple[2];
			}
			//end::sql-hibernate-composite-key-entity-associations_named-query-example[]
			assertEquals(1, tuples.size());
		});
	}
