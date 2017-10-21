	@Test
	public void test_jpql_api_parameter_example_1() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::jpql-api-parameter-example[]
			Query query = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like :name" )
			.setParameter( "name", "J%" );
			//end::jpql-api-parameter-example[]
		});
	}
