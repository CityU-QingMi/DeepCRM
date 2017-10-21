	@Test
	public void test_jpql_api_single_result_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::jpql-api-single-result-example[]
			Person person = (Person) entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like :name" )
			.setParameter( "name", "J%" )
			.getSingleResult();
			//end::jpql-api-single-result-example[]
		});
	}
