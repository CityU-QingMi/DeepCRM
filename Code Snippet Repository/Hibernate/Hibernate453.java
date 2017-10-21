	@Test
	public void test_sql_jpa_scalar_named_query_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::sql-jpa-scalar-named-query-example[]
			List<String> names = entityManager.createNamedQuery(
				"find_person_name" )
			.getResultList();
			//end::sql-jpa-scalar-named-query-example[]
			assertEquals(3, names.size());
		});
	}
