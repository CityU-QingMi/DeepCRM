	@Test
	public void test_hql_length_function_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-length-function-example[]
			List<Integer> lengths = entityManager.createQuery(
				"select length( p.name ) " +
				"from Person p ", Integer.class )
			.getResultList();
			//end::hql-length-function-example[]
			assertEquals(3, lengths.size());
		});
	}
