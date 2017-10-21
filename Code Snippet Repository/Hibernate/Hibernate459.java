	@Test
	public void test_sql_hibernate_multiple_scalar_values_dto_named_query_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::sql-hibernate-multiple-scalar-values-dto-named-query-example[]
			List<PersonNames> personNames = session.getNamedQuery(
				"find_person_name_and_nickName_dto" )
			.list();
			//end::sql-hibernate-multiple-scalar-values-dto-named-query-example[]
			assertEquals(3, personNames.size());
		});
	}
