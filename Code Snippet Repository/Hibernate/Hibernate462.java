	@Test
	public void test_sql_hibernate_entity_named_query_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::sql-hibernate-entity-named-query-example[]
			List<Person> persons = session.getNamedQuery(
				"find_person_by_name" )
			.setParameter("name", "J%")
			.list();
			//end::sql-hibernate-entity-named-query-example[]
			assertEquals(1, persons.size());
		});
	}
