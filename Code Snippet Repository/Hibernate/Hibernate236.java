	@Test
	public void test_hql_year_function_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-year-function-example[]
			List<Integer> years = entityManager.createQuery(
				"select year( c.timestamp ) " +
				"from Call c ", Integer.class )
			.getResultList();
			//end::hql-year-function-example[]
			assertEquals(2, years.size());
		});
	}
