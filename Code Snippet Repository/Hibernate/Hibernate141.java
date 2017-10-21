	@Test
	public void test_hql_between_predicate_example_1() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-between-predicate-example[]
			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"join p.phones ph " +
				"where p.id = 1L and index(ph) between 0 and 3", Person.class )
			.getResultList();
			//end::hql-between-predicate-example[]
			assertEquals(1, persons.size());
		});
	}
