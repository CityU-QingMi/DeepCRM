	@Test
	public void test_hql_sqrt_function_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-sqrt-function-example[]
			List<Double> sqrts = entityManager.createQuery(
				"select sqrt( c.duration ) " +
				"from Call c ", Double.class )
			.getResultList();
			//end::hql-sqrt-function-example[]
			assertEquals(2, sqrts.size());
		});
	}
