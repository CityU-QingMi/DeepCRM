	@Test
	public void test_jpql_api_hibernate_named_query_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::jpql-api-hibernate-named-query-example[]
			Phone phone = entityManager
				.createNamedQuery( "get_phone_by_number", Phone.class )
				.setParameter( "number", "123-456-7890" )
				.getSingleResult();
			//end::jpql-api-hibernate-named-query-example[]
			assertNotNull( phone );
		});
	}
