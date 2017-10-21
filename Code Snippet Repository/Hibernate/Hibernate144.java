	@Test
	public void test_hql_between_predicate_example_3() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-between-predicate-example[]

			List<Call> calls = entityManager.createQuery(
				"select c " +
				"from Call c " +
				"where c.duration between 5 and 20", Call.class )
			.getResultList();
			//end::hql-between-predicate-example[]
			assertEquals(1, calls.size());
		});
	}
