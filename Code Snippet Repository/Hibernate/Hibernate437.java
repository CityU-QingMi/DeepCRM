	@Test
	public void test_sql_jpa_entity_associations_query_many_to_one_example() {
        doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::sql-jpa-entity-associations-query-many-to-one-example[]
			List<Phone> phones = entityManager.createNativeQuery(
				"SELECT id, phone_number, phone_type, person_id " +
				"FROM Phone", Phone.class )
			.getResultList();
			//end::sql-jpa-entity-associations-query-many-to-one-example[]
			assertEquals(3, phones.size());
		});
	}
