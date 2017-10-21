	@Test
	public void test_hql_string_literals_example_4() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-numeric-literals-example[]

			// simple integer literal, typed as a long
			Person person = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.id = 1L", Person.class)
			.getSingleResult();
			//end::hql-numeric-literals-example[]
		});
	}
