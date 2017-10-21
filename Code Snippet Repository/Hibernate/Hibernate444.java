	@Test
	@RequiresDialect(H2Dialect.class)
	@RequiresDialect(Oracle8iDialect.class)
	@RequiresDialect(PostgreSQL82Dialect.class)
	public void test_sql_hibernate_entity_associations_query_one_to_many_join_example_2() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::sql-hibernate-entity-associations-query-one-to-many-join-example[]
			List<Object[]> tuples = session.createNativeQuery(
				"SELECT * " +
				"FROM Phone ph " +
				"JOIN phone_call c ON c.phone_id = ph.id" )
			.addEntity("phone", Phone.class )
			.addJoin( "c", "phone.calls")
			.list();

			for(Object[] tuple : tuples) {
				Phone phone = (Phone) tuple[0];
				Call call = (Call) tuple[1];
			}
			//end::sql-hibernate-entity-associations-query-one-to-many-join-example[]
			assertEquals(2, tuples.size());
		});
	}
