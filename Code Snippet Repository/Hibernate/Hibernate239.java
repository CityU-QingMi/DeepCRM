	@Test
	@RequiresDialect(SQLServerDialect.class)
	public void test_hql_str_function_example_sql_server() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-str-function-example[]
			List<String> timestamps = entityManager.createQuery(
				"select str( cast(duration as float) / 60, 4, 2 ) " +
				"from Call c ", String.class )
			.getResultList();
			//end::hql-str-function-example[]
			assertEquals(2, timestamps.size());
		});
	}
