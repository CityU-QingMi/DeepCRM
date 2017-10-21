	@Test
	public void test_sql_jpa_entity_query_example() {
        doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::sql-jpa-entity-query-example[]
			List<Person> persons = entityManager.createNativeQuery(
				"SELECT * FROM Person", Person.class )
			.getResultList();
			//end::sql-jpa-entity-query-example[]
			assertEquals(3, persons.size());
		});
	}
