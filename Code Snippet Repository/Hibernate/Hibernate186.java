	@Test
	public void test_jpql_api_list_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::jpql-api-list-example[]
			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like :name" )
			.setParameter( "name", "J%" )
			.getResultList();
			//end::jpql-api-list-example[]
		});
	}
