	@Test
	public void test_sql_jpa_multiple_scalar_values_named_query_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::sql-jpa-multiple-scalar-values-named-query-example[]
			List<Object[]> tuples = entityManager.createNamedQuery(
				"find_person_name_and_nickName" )
			.getResultList();

			for(Object[] tuple : tuples) {
				String name = (String) tuple[0];
				String nickName = (String) tuple[1];
			}
			//end::sql-jpa-multiple-scalar-values-named-query-example[]
			assertEquals(3, tuples.size());
		});
	}
