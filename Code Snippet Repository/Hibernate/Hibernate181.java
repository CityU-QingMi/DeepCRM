	@Test
	public void test_jpql_api_basic_usage_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::jpql-api-basic-usage-example[]
			Query query = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like :name" )
			// timeout - in milliseconds
			.setHint( "javax.persistence.query.timeout", 2000 )
			// flush only at commit time
			.setFlushMode( FlushModeType.COMMIT );
			//end::jpql-api-basic-usage-example[]
		});
	}
