	@Test
	public void test_hql_api_positional_parameter_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Date timestamp = new Date(  );
			Session session = entityManager.unwrap( Session.class );
			//tag::hql-api-positional-parameter-example[]
			org.hibernate.query.Query query = session.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like ? " )
			.setParameter( 0, "J%" );
			//end::hql-api-positional-parameter-example[]
		});
	}
