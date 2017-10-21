	@Test
	public void test_sql_jpa_multiple_scalar_values_dto_named_query_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::sql-jpa-multiple-scalar-values-dto-named-query-example[]
			List<PersonNames> personNames = entityManager.createNamedQuery(
				"find_person_name_and_nickName_dto" )
			.getResultList();
			//end::sql-jpa-multiple-scalar-values-dto-named-query-example[]
			assertEquals(3, personNames.size());
		});
	}
