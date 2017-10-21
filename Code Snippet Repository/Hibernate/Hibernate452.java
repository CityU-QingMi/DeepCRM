	@Test
	public void test_sql_hibernate_query_parameters_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::sql-hibernate-query-parameters-example[]
			List<Person> persons = session.createNativeQuery(
				"SELECT * " +
				"FROM Person " +
				"WHERE name like :name" )
			.addEntity( Person.class )
			.setParameter("name", "J%")
			.list();
			//end::sql-hibernate-query-parameters-example[]
			assertEquals(1, persons.size());
		});
	}
