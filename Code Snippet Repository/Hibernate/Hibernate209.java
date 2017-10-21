	@Test
	public void test_hql_string_literals_example_8() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-numeric-literals-example[]

			// scientific notation, typed as a float
			List<Call> calls = entityManager.createQuery(
				"select c " +
				"from Call c " +
				"where c.duration > 1e+2F", Call.class )
			.getResultList();
			//end::hql-numeric-literals-example[]
		});

	}
