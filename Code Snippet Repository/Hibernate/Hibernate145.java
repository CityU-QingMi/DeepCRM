	@Test
	public void test_hql_between_predicate_example_4() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-between-predicate-example[]

			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.name between 'H' and 'M'", Person.class )
			.getResultList();
			//end::hql-between-predicate-example[]
			assertEquals(1, persons.size());
		});
	}
