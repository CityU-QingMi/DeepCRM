	@Test
	public void test_hql_lower_function_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-lower-function-example[]
			List<String> names = entityManager.createQuery(
				"select lower( p.name ) " +
				"from Person p ", String.class )
			.getResultList();
			//end::hql-lower-function-example[]
			assertEquals(3, names.size());
		});
	}
