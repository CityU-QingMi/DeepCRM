	@Test
	public void test_hql_api_unique_result_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::hql-api-unique-result-example[]
			Person person = (Person) session.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like :name" )
			.setParameter( "name", "J%" )
			.uniqueResult();
			//end::hql-api-unique-result-example[]
		});
	}
