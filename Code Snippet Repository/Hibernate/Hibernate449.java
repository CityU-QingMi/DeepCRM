	@Test
	public void test_sql_hibernate_dto_query_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			//tag::sql-hibernate-dto-query-example[]
			List<PersonSummaryDTO> dtos = session.createNativeQuery(
				"SELECT p.id as \"id\", p.name as \"name\" " +
				"FROM Person p")
			.setResultTransformer( Transformers.aliasToBean( PersonSummaryDTO.class ) )
			.list();
			//end::sql-hibernate-dto-query-example[]
			assertEquals(3, dtos.size());
		});
	}
