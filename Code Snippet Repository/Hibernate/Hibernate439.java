	@Test
	public void test_sql_jpa_entity_associations_query_many_to_one_join_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::sql-jpa-entity-associations-query-many-to-one-join-example[]
			List<Phone> phones = entityManager.createNativeQuery(
				"SELECT * " +
				"FROM Phone ph " +
				"JOIN Person pr ON ph.person_id = pr.id", Phone.class )
			.getResultList();

			for(Phone phone : phones) {
				assertNotNull( phone.getPerson().getName() );
			}
			//end::sql-jpa-entity-associations-query-many-to-one-join-example[]
			assertEquals(3, phones.size());
		});
	}
