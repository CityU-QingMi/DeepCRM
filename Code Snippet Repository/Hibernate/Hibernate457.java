	@Test
	public void test_sql_hibernate_multiple_scalar_values_named_query_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::sql-hibernate-multiple-scalar-values-named-query-example[]
			List<Object[]> tuples = session.getNamedQuery(
				"find_person_name_and_nickName" )
			.list();

			for(Object[] tuple : tuples) {
				String name = (String) tuple[0];
				String nickName = (String) tuple[1];
			}
			//end::sql-hibernate-multiple-scalar-values-named-query-example[]
			assertEquals(3, tuples.size());
		});
	}
