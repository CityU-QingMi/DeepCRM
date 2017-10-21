	@Test
	public void test_jpql_api_named_query_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::jpql-api-named-query-example[]
			Query query = entityManager.createNamedQuery( "get_person_by_name" );

			TypedQuery<Person> typedQuery = entityManager.createNamedQuery(
				"get_person_by_name", Person.class
			);
			//end::jpql-api-named-query-example[]
		});
	}
