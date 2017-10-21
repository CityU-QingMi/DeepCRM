	@Test
	public void test_hql_null_if_example_2() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-nullif-example[]

			// equivalent CASE expression
			List<String> nickNames = entityManager.createQuery(
				"select " +
				"	case" +
				"	when p.nickName = p.name" +
				"	then null" +
				"	else p.nickName" +
				"	end " +
				"from Person p", String.class )
			.getResultList();
			//end::hql-nullif-example[]
			assertEquals(3, nickNames.size());
		});
	}
