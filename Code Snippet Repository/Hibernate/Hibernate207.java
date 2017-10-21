	@Test
	public void test_hql_string_literals_example_6() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-numeric-literals-example[]

			// decimal notation, typed as a float
			List<Call> calls = entityManager.createQuery(
				"select c " +
				"from Call c " +
				"where c.duration > 100.5F", Call.class )
			.getResultList();
			//end::hql-numeric-literals-example[]
		});
	}
