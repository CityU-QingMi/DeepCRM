	@Test
	public void test_sql_hibernate_entity_associations_query_many_to_one_example() {
        doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::sql-hibernate-entity-associations-query-many-to-one-example[]
			List<Phone> phones = session.createNativeQuery(
				"SELECT id, phone_number, phone_type, person_id " +
				"FROM Phone" )
			.addEntity( Phone.class )
			.list();
			//end::sql-hibernate-entity-associations-query-many-to-one-example[]
			assertEquals(3, phones.size());
		});
	}
