	@Test
	@SkipForDialect(SQLServerDialect.class)
	public void test_hql_current_date_function_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-current-date-function-example[]
			List<Call> calls = entityManager.createQuery(
				"select c " +
				"from Call c " +
				"where c.timestamp = current_date", Call.class )
			.getResultList();
			//end::hql-current-date-function-example[]
			assertEquals(0, calls.size());
		});
	}
