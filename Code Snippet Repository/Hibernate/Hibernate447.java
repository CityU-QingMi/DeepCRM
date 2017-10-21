	@Test
	public void test_sql_hibernate_multi_entity_query_example() {
		try {
			doInJPA( this::entityManagerFactory, entityManager -> {
				Session session = entityManager.unwrap( Session.class );
				//tag::sql-hibernate-multi-entity-query-example[]
				List<Object> entities = session.createNativeQuery(
						"SELECT * " +
								"FROM Person pr, Partner pt " +
								"WHERE pr.name = pt.name" )
						.list();
				//end::sql-hibernate-multi-entity-query-example[]
				assertEquals( 2, entities.size() );
			} );
			fail( "Should throw NonUniqueDiscoveredSqlAliasException!" );
		}
		catch (NonUniqueDiscoveredSqlAliasException e) {
			// expected
		}
		catch (PersistenceException e) {
			assertTyping( NonUniqueDiscoveredSqlAliasException.class, e.getCause() );
		}
	}
