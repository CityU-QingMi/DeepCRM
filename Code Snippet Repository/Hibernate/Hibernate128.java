	@Test
	public void test_hql_relational_comparisons_example_2() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-relational-comparisons-example[]

			// string comparison
			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like 'John%' ", Person.class )
			.getResultList();
			//end::hql-relational-comparisons-example[]
			assertEquals(1, persons.size());
		});
	}
