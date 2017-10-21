	@Test
	public void test_hql_string_literals_example_2() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-string-literals-example[]

			// Escaping quotes
			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like 'Joe''s'", Person.class)
			.getResultList();
			//end::hql-string-literals-example[]
		});
	}
