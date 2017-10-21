	@Test
	public void test_searched_case_expressions_example_1() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-searched-case-expressions-example[]
			List<String> nickNames = entityManager.createQuery(
				"select " +
				"	case " +
				"	when p.nickName is null " +
				"	then " +
				"		case " +
				"		when p.name is null " +
				"		then '<no nick name>' " +
				"		else p.name " +
				"		end" +
				"	else p.nickName " +
				"	end " +
				"from Person p", String.class )
			.getResultList();
			//end::hql-searched-case-expressions-example[]
			assertEquals(3, nickNames.size());
		});
	}
