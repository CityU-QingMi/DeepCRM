	@Test
	public void test_sql_hibernate_query_scalar_partial_explicit_result_set_example() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::sql-hibernate-scalar-query-partial-explicit-result-set-example[]
			List<Object[]> persons = session.createNativeQuery(
				"SELECT * FROM Person" )
			.addScalar( "id", LongType.INSTANCE )
			.addScalar( "name" )
			.list();

			for(Object[] person : persons) {
				Long id = (Long) person[0];
				String name = (String) person[1];
			}
			//end::sql-hibernate-scalar-query-partial-explicit-result-set-example[]
			assertEquals(3, persons.size());
		});
	}
