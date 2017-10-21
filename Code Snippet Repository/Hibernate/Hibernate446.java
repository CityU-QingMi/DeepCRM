	@Test
	public void test_sql_jpa_multi_entity_query_example() {
		try {
			doInJPA( this::entityManagerFactory, entityManager -> {
				//tag::sql-jpa-multi-entity-query-example[]
				List<Object> entities = entityManager.createNativeQuery(
					"SELECT * " +
					"FROM Person pr, Partner pt " +
					"WHERE pr.name = pt.name" )
				.getResultList();
				//end::sql-jpa-multi-entity-query-example[]
				assertEquals(2, entities.size());
			});
			fail("Should throw NonUniqueDiscoveredSqlAliasException!");
		}
		catch (PersistenceException expected) {
			assertEquals( NonUniqueDiscoveredSqlAliasException.class, expected.getCause().getClass() );
		}
	}
