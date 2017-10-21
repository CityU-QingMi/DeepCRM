	@Test
	public void test_sql_hibernate_entity_associations_query_many_to_one_join_result_transformer_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::sql-hibernate-entity-associations-query-many-to-one-join-result-transformer-example[]
			List<Person> persons = session.createNativeQuery(
				"SELECT * " +
				"FROM Phone ph " +
				"JOIN Person pr ON ph.person_id = pr.id" )
			.addEntity("phone", Phone.class )
			.addJoin( "pr", "phone.person")
			.setResultTransformer( Criteria.ROOT_ENTITY )
			.list();

			for(Person person : persons) {
				person.getPhones();
			}
			//end::sql-hibernate-entity-associations-query-many-to-one-join-result-transformer-example[]
			assertEquals(3, persons.size());
		});
	}
