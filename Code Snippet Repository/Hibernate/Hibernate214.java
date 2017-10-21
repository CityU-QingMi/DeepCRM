	@Test
	public void test_hql_aggregate_functions_example_1() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-aggregate-functions-example[]
			Object[] callStatistics = entityManager.createQuery(
				"select " +
				"	count(c), " +
				"	sum(c.duration), " +
				"	min(c.duration), " +
				"	max(c.duration), " +
				"	avg(c.duration)  " +
				"from Call c ", Object[].class )
			.getSingleResult();
			//end::hql-aggregate-functions-example[]
			assertNotNull(callStatistics);
		});
	}
