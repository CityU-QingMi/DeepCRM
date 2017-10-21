	@Test
	public void test_hql_string_literals_example_3() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-numeric-literals-example[]
			// simple integer literal
			Person person = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.id = 1", Person.class)
			.getSingleResult();
			//end::hql-numeric-literals-example[]
		});
	}
