	@Test
	@RequiresDialect(H2Dialect.class)
	@RequiresDialect(Oracle8iDialect.class)
	@RequiresDialect(PostgreSQL82Dialect.class)
	public void test_sql_jpa_entity_associations_named_query_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::sql-jpa-entity-associations_named-query-example[]
			List<Object[]> tuples = entityManager.createNamedQuery(
				"find_person_with_phones_by_name" )
			.setParameter("name", "J%")
			.getResultList();

			for(Object[] tuple : tuples) {
				Person person = (Person) tuple[0];
				Phone phone = (Phone) tuple[1];
			}
			//end::sql-jpa-entity-associations_named-query-example[]
			assertEquals(1, tuples.size());
		});
	}
