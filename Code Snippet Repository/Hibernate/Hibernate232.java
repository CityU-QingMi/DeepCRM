	@Test
	public void test_hql_current_timestamp_function_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-current-timestamp-function-example[]
			List<Call> calls = entityManager.createQuery(
				"select c " +
				"from Call c " +
				"where c.timestamp = current_timestamp", Call.class )
			.getResultList();
			//end::hql-current-timestamp-function-example[]
			assertEquals(0, calls.size());
		});
	}
