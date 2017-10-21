	@Test
	public void test_sql_hibernate_query_scalar_example() {

        doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::sql-hibernate-all-columns-scalar-query-example[]
			List<Object[]> persons = session.createNativeQuery(
				"SELECT * FROM Person" )
			.list();
			//end::sql-hibernate-all-columns-scalar-query-example[]
			assertEquals(3, persons.size());
		});
	}
