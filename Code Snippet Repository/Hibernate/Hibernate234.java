	@Test
	public void test_hql_cast_function_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-cast-function-example[]
			List<String> durations = entityManager.createQuery(
				"select cast( c.duration as string ) " +
				"from Call c ", String.class )
			.getResultList();
			//end::hql-cast-function-example[]
			assertEquals(2, durations.size());
		});
	}
