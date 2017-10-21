	@Test
	public void test_sql_hibernate_entity_query_example() {
        doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::sql-hibernate-entity-query-example[]
			List<Person> persons = session.createNativeQuery(
				"SELECT * FROM Person" )
			.addEntity( Person.class )
			.list();
			//end::sql-hibernate-entity-query-example[]
			assertEquals(3, persons.size());
		});
	}
