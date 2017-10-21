	@Test
	public void test_sql_hibernate_custom_column_selection_scalar_query_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::sql-hibernate-custom-column-selection-scalar-query-example[]
			List<Object[]> persons = session.createNativeQuery(
				"SELECT id, name FROM Person" )
			.list();

			for(Object[] person : persons) {
				Number id = (Number) person[0];
				String name = (String) person[1];
			}
			//end::sql-hibernate-custom-column-selection-scalar-query-example[]
			assertEquals(3, persons.size());
		});
	}
