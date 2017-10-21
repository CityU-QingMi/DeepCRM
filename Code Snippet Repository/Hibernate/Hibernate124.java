	@Test
	public void test_hql_select_clause_dynamic_instantiation_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-select-clause-dynamic-instantiation-example[]
			CallStatistics callStatistics = entityManager.createQuery(
				"select new org.hibernate.userguide.hql.CallStatistics(" +
				"	count(c), " +
				"	sum(c.duration), " +
				"	min(c.duration), " +
				"	max(c.duration), " +
				"	avg(c.duration)" +
				")  " +
				"from Call c ", CallStatistics.class )
			.getSingleResult();
			//end::hql-select-clause-dynamic-instantiation-example[]
			assertNotNull(callStatistics);
		});
	}
