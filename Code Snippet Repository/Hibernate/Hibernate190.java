	@Test
	public void test_hql_api_parameter_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::hql-api-parameter-example[]
			org.hibernate.query.Query query = session.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like :name" )
			.setParameter( "name", "J%", StringType.INSTANCE );
			//end::hql-api-parameter-example[]
		});
	}
