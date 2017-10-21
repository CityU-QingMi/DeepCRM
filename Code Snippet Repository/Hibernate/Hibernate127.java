	@Test
	public void test_hql_relational_comparisons_example_1() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-relational-comparisons-example[]
			// numeric comparison
			List<Call> calls = entityManager.createQuery(
				"select c " +
				"from Call c " +
				"where c.duration < 30 ", Call.class )
			.getResultList();
			//end::hql-relational-comparisons-example[]
			assertEquals(1, calls.size());
		});
	}
