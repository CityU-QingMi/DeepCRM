	@Test
	public void test_hql_substring_function_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-substring-function-example[]
			List<String> prefixes = entityManager.createQuery(
				"select substring( p.number, 1, 2 ) " +
				"from Call c " +
				"join c.phone p", String.class )
			.getResultList();
			//end::hql-substring-function-example[]
			assertEquals(2, prefixes.size());
		});
	}
