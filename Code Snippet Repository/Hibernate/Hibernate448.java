	@Test
	public void test_sql_hibernate_multi_entity_query_alias_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::sql-hibernate-multi-entity-query-alias-example[]
			List<Object> entities = session.createNativeQuery(
				"SELECT {pr.*}, {pt.*} " +
				"FROM Person pr, Partner pt " +
				"WHERE pr.name = pt.name" )
			.addEntity( "pr", Person.class)
			.addEntity( "pt", Partner.class)
			.list();
			//end::sql-hibernate-multi-entity-query-alias-example[]
			assertEquals(1, entities.size());
		});
	}
