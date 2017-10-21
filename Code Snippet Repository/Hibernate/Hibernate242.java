	@Test
	public void test_hql_collection_expressions_example_3() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-collection-expressions-example[]

			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where maxindex( p.phones ) = 0", Person.class )
			.getResultList();
			//end::hql-collection-expressions-example[]
			assertEquals(1, persons.size());
		});
	}
