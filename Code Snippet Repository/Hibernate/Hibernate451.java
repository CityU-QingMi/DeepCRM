	@Test
	public void test_sql_jpa_query_parameters_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::sql-jpa-query-parameters-example[]
			List<Person> persons = entityManager.createNativeQuery(
				"SELECT * " +
				"FROM Person " +
				"WHERE name like :name", Person.class )
			.setParameter("name", "J%")
			.getResultList();
			//end::sql-jpa-query-parameters-example[]
			assertEquals(1, persons.size());
		});
	}
