	@Test
	public void test_hql_string_literals_example_5() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-numeric-literals-example[]

			// decimal notation
			List<Call> calls = entityManager.createQuery(
				"select c " +
				"from Call c " +
				"where c.duration > 100.5", Call.class )
			.getResultList();
			//end::hql-numeric-literals-example[]
		});
	}
