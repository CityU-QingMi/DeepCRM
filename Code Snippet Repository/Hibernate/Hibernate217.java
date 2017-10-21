	@Test
	public void test_hql_aggregate_functions_example_3() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-aggregate-functions-example[]

			List<Object[]> callCount = entityManager.createQuery(
				"select p.number, count(c) " +
				"from Call c " +
				"join c.phone p " +
				"group by p.number", Object[].class )
			.getResultList();
			//end::hql-aggregate-functions-example[]
			assertNotNull(callCount.get( 0 ));
		});
	}
