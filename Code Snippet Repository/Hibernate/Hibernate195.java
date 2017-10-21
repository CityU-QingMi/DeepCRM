	@Test
	public void test_hql_api_list_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::hql-api-list-example[]
			List<Person> persons = session.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like :name" )
			.setParameter( "name", "J%" )
			.list();
			//end::hql-api-list-example[]
		});
	}
