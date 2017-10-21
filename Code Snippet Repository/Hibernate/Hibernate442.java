	@Test
	@RequiresDialect(H2Dialect.class)
	@RequiresDialect(Oracle8iDialect.class)
	@RequiresDialect(PostgreSQL82Dialect.class)
	public void test_sql_jpa_entity_associations_query_one_to_many_join_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::sql-jpa-entity-associations-query-one-to-many-join-example[]
			List<Phone> phones = entityManager.createNativeQuery(
				"SELECT * " +
				"FROM Phone ph " +
				"JOIN phone_call c ON c.phone_id = ph.id", Phone.class )
			.getResultList();

			for(Phone phone : phones) {
				List<Call> calls = phone.getCalls();
			}
			//end::sql-jpa-entity-associations-query-one-to-many-join-example[]
			assertEquals(2, phones.size());
		});
	}
