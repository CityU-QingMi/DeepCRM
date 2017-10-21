	@Test
	public void test_hql_collection_expressions_example_6() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-collection-expressions-example[]

			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where exists elements ( p.phones )", Person.class )
			.getResultList();
			//end::hql-collection-expressions-example[]
			assertEquals(2, persons.size());
		});
	}
