	@Test
	public void test_jpql_api_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::jpql-api-example[]
			Query query = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like :name"
			);

			TypedQuery<Person> typedQuery = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like :name", Person.class
			);
			//end::jpql-api-example[]
		});
	}
