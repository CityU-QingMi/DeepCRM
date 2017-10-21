	@Test
	public void test_sql_hibernate_entity_associations_query_many_to_one_join_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::sql-hibernate-entity-associations-query-many-to-one-join-example[]
			List<Object[]> tuples = session.createNativeQuery(
				"SELECT * " +
				"FROM Phone ph " +
				"JOIN Person pr ON ph.person_id = pr.id" )
			.addEntity("phone", Phone.class )
			.addJoin( "pr", "phone.person")
			.list();

			for(Object[] tuple : tuples) {
				Phone phone = (Phone) tuple[0];
				Person person = (Person) tuple[1];
				assertNotNull( person.getName() );
			}
			//end::sql-hibernate-entity-associations-query-many-to-one-join-example[]
			assertEquals(3, tuples.size());
		});
	}
