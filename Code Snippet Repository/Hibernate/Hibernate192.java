	@Test
	public void test_hql_api_parameter_short_form_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Date timestamp = new Date(  );
			Session session = entityManager.unwrap( Session.class );
			//tag::hql-api-parameter-short-form-example[]
			org.hibernate.query.Query query = session.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like :name " +
				"  and p.createdOn > :timestamp" )
			.setParameter( "name", "J%" )
			.setParameter( "timestamp", timestamp, TemporalType.TIMESTAMP);
			//end::hql-api-parameter-short-form-example[]
		});
	}
