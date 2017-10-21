	@Test
	public void test_hql_abs_function_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-abs-function-example[]
			List<Integer> abs = entityManager.createQuery(
				"select abs( c.duration ) " +
				"from Call c ", Integer.class )
			.getResultList();
			//end::hql-abs-function-example[]
			assertEquals(2, abs.size());
		});
	}
