	@Test
	@RequiresDialect(H2Dialect.class)
	@RequiresDialect(Oracle8iDialect.class)
	@RequiresDialect(PostgreSQL82Dialect.class)
	public void test_sql_hibernate_entity_associations_named_query_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::sql-hibernate-entity-associations_named-query-example[]
			List<Object[]> tuples = session.getNamedQuery(
				"find_person_with_phones_by_name" )
			.setParameter("name", "J%")
			.list();

			for(Object[] tuple : tuples) {
				Person person = (Person) tuple[0];
				Phone phone = (Phone) tuple[1];
			}
			//end::sql-hibernate-entity-associations_named-query-example[]
			assertEquals(1, tuples.size());
		});
	}
