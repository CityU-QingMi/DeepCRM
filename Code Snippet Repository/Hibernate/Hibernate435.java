	@Test
	public void test_sql_jpa_entity_query_explicit_result_set_example() {
        doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::sql-jpa-entity-query-explicit-result-set-example[]
			List<Person> persons = entityManager.createNativeQuery(
				"SELECT id, name, nickName, address, createdOn, version " +
				"FROM Person", Person.class )
			.getResultList();
			//end::sql-jpa-entity-query-explicit-result-set-example[]
			assertEquals(3, persons.size());
		});
	}
