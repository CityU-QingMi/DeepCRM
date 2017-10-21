	@Test
	public void test_hql_upper_function_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-upper-function-example[]
			List<String> names = entityManager.createQuery(
				"select upper( p.name ) " +
				"from Person p ", String.class )
			.getResultList();
			//end::hql-upper-function-example[]
			assertEquals(3, names.size());
		});
	}
