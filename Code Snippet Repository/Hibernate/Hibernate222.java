	@Test
	public void test_hql_trim_function_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-trim-function-example[]
			List<String> names = entityManager.createQuery(
				"select trim( p.name ) " +
				"from Person p ", String.class )
			.getResultList();
			//end::hql-trim-function-example[]
			assertEquals(3, names.size());
		});
	}
