	@Test
	public void test_hql_collection_expressions_example_10() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-collection-expressions-example[]

			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where size( p.phones ) = 2", Person.class )
			.getResultList();
			//end::hql-collection-expressions-example[]
			assertEquals(1, persons.size());
		});
	}
