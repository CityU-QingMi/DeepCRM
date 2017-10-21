	@Test
	public void test_sql_jpa_all_columns_scalar_query_example() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::sql-jpa-all-columns-scalar-query-example[]
			List<Object[]> persons = entityManager.createNativeQuery(
				"SELECT * FROM Person" )
			.getResultList();
			//end::sql-jpa-all-columns-scalar-query-example[]
			assertEquals( 3, persons.size() );
		} );
	}
