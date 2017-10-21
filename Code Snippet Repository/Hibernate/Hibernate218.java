	@Test
	public void test_hql_concat_function_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-concat-function-example[]
			List<String> callHistory = entityManager.createQuery(
				"select concat( p.number, ' : ' , cast(c.duration as string) ) " +
				"from Call c " +
				"join c.phone p", String.class )
			.getResultList();
			//end::hql-concat-function-example[]
			assertEquals(2, callHistory.size());
		});
	}
