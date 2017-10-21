	@Test
	public void test_jpql_api_parameter_example_2() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Date timestamp = new Date(  );
			//tag::jpql-api-parameter-example[]

			// For generic temporal field types (e.g. `java.util.Date`, `java.util.Calendar`)
			// we also need to provide the associated `TemporalType`
			Query query = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.createdOn > :timestamp" )
			.setParameter( "timestamp", timestamp, TemporalType.DATE );
			//end::jpql-api-parameter-example[]
		});
	}
