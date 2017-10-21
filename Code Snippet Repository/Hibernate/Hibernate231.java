	@Test @RequiresDialect(H2Dialect.class)
	public void test_hql_current_time_function_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-current-time-function-example[]
			List<Call> calls = entityManager.createQuery(
				"select c " +
				"from Call c " +
				"where c.timestamp = current_time", Call.class )
			.getResultList();
			//end::hql-current-time-function-example[]
			assertEquals(0, calls.size());
		});
	}
