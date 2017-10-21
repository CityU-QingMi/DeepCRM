	@Test
	@RequiresDialect(SQLServerDialect.class)
	public void test_hql_current_date_function_example_sql_server() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			List<Call> calls = entityManager.createQuery(
				"select c " +
				"from Call c " +
				"where c.timestamp = current_date()", Call.class )
			.getResultList();
			assertEquals(0, calls.size());
		});
	}
