	@Test
	public void test_simple_case_expressions_example_2() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-simple-case-expressions-example[]

			// same as above
			List<String> nickNames = entityManager.createQuery(
				"select coalesce(p.nickName, '<no nick name>') " +
				"from Person p", String.class )
			.getResultList();
			//end::hql-simple-case-expressions-example[]
			assertEquals(3, nickNames.size());
		});
	}
