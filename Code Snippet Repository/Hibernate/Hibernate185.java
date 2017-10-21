	@Test
	public void test_jpql_api_positional_parameter_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::jpql-api-positional-parameter-example[]
			Query query = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like ?1" )
			.setParameter( 1, "J%" );
			//end::jpql-api-positional-parameter-example[]
		});
	}
