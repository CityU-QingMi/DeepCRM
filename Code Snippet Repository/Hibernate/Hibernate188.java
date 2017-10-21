	@Test
	public void test_hql_api_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::hql-api-example[]
			org.hibernate.query.Query query = session.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like :name"
			);
			//end::hql-api-example[]
		});
	}
