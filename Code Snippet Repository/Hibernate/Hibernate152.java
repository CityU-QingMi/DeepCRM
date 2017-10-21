	@Test
	public void test_hql_empty_collection_predicate_example_1() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-empty-collection-predicate-example[]
			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.phones is empty", Person.class )
			.getResultList();
			//end::hql-empty-collection-predicate-example[]
			assertEquals(1, persons.size());
		});
	}
