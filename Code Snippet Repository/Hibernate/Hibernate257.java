	@Test
	public void test_simple_case_expressions_example_1() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-simple-case-expressions-example[]
			List<String> nickNames = entityManager.createQuery(
				"select " +
				"	case p.nickName " +
				"	when 'NA' " +
				"	then '<no nick name>' " +
				"	else p.nickName " +
				"	end " +
				"from Person p", String.class )
			.getResultList();
			//end::hql-simple-case-expressions-example[]
			assertEquals(3, nickNames.size());
		});
	}
