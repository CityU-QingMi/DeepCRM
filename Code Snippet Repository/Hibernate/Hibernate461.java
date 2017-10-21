	@Test
	public void test_sql_jpa_entity_named_query_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::sql-jpa-entity-named-query-example[]
			List<Person> persons = entityManager.createNamedQuery(
				"find_person_by_name" )
			.setParameter("name", "J%")
			.getResultList();
			//end::sql-jpa-entity-named-query-example[]
			assertEquals(1, persons.size());
		});
	}
