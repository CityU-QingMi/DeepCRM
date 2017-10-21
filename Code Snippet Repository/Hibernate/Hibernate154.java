	@Test
	public void test_hql_empty_collection_predicate_example_2() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-empty-collection-predicate-example[]

			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.phones is not empty", Person.class )
			.getResultList();
			//end::hql-empty-collection-predicate-example[]
			assertEquals(2, persons.size());
		});
	}
