	@Test
	public void test_hql_api_parameter_inferred_type_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::hql-api-parameter-inferred-type-example[]
			org.hibernate.query.Query query = session.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like :name" )
			.setParameter( "name", "J%" );
			//end::hql-api-parameter-inferred-type-example[]
		});
	}
