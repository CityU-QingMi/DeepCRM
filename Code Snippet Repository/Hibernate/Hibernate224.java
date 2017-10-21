	@Test
	public void test_hql_locate_function_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-locate-function-example[]
			List<Integer> sizes = entityManager.createQuery(
				"select locate( 'John', p.name ) " +
				"from Person p ", Integer.class )
			.getResultList();
			//end::hql-locate-function-example[]
			assertEquals(3, sizes.size());
		});
	}
