	@Test
	public void test_sql_hibernate_scalar_named_query_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::sql-hibernate-scalar-named-query-example[]
			List<String> names = session.getNamedQuery(
				"find_person_name" )
			.list();
			//end::sql-hibernate-scalar-named-query-example[]
			assertEquals(3, names.size());
		});
	}
