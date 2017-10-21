	@Test
	public void test_hql_member_of_collection_predicate_example_1() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-member-of-collection-predicate-example[]
			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where 'Home address' member of p.addresses", Person.class )
			.getResultList();
			//end::hql-member-of-collection-predicate-example[]
			assertEquals(1, persons.size());
		});
	}
