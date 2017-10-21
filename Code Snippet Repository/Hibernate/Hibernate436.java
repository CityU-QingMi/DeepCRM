	@Test
	public void test_sql_hibernate_entity_query_explicit_result_set_example() {
        doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::sql-hibernate-entity-query-explicit-result-set-example[]
			List<Person> persons = session.createNativeQuery(
				"SELECT id, name, nickName, address, createdOn, version " +
				"FROM Person" )
			.addEntity( Person.class )
			.list();
			//end::sql-hibernate-entity-query-explicit-result-set-example[]
			assertEquals(3, persons.size());
		});
	}
