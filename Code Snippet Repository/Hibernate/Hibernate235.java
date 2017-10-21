	@Test
	public void test_hql_extract_function_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-extract-function-example[]
			List<Integer> years = entityManager.createQuery(
				"select extract( YEAR from c.timestamp ) " +
				"from Call c ", Integer.class )
			.getResultList();
			//end::hql-extract-function-example[]
			assertEquals(2, years.size());
		});
	}
