	@Test
	public void test_hql_string_literals_example_1() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-string-literals-example[]
			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like 'Joe'", Person.class)
			.getResultList();
			//end::hql-string-literals-example[]
		});
	}
