	@Test
	public void test_sql_jpa_custom_column_selection_scalar_query_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::sql-jpa-custom-column-selection-scalar-query-example[]
			List<Object[]> persons = entityManager.createNativeQuery(
				"SELECT id, name FROM Person" )
			.getResultList();

			for(Object[] person : persons) {
				Number id = (Number) person[0];
				String name = (String) person[1];
			}
			//end::sql-jpa-custom-column-selection-scalar-query-example[]
			assertEquals(3, persons.size());
		});
	}
