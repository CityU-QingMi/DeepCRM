	@Test
	@SkipForDialect(SQLServerDialect.class)
	public void test_hql_str_function_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-str-function-example[]
			List<String> timestamps = entityManager.createQuery(
				"select str( c.timestamp ) " +
				"from Call c ", String.class )
			.getResultList();
			//end::hql-str-function-example[]
			assertEquals(2, timestamps.size());
		});
	}
