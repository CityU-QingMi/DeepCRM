	@Test
	public void test_hql_aggregate_functions_example_2() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-aggregate-functions-example[]

			Long phoneCount = entityManager.createQuery(
				"select count( distinct c.phone ) " +
				"from Call c ", Long.class )
			.getSingleResult();
			//end::hql-aggregate-functions-example[]
			assertNotNull(phoneCount);
		});
	}
