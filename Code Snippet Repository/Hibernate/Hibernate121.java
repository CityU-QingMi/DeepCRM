	@Test
	public void test_searched_case_expressions_example_2() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-searched-case-expressions-example[]

			// coalesce can handle this more succinctly
			List<String> nickNames = entityManager.createQuery(
				"select coalesce( p.nickName, p.name, '<no nick name>' ) " +
				"from Person p", String.class )
			.getResultList();
			//end::hql-searched-case-expressions-example[]
			assertEquals(3, nickNames.size());
		});
	}
