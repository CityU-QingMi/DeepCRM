	@Test
	public void test_hql_explicit_fetch_join_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-explicit-fetch-join-example[]

			// functionally the same query but using the 'left outer' phrase
			List<Person> persons = entityManager.createQuery(
				"select distinct pr " +
				"from Person pr " +
				"left join fetch pr.phones ", Person.class )
			.getResultList();
			//end::hql-explicit-fetch-join-example[]
			assertEquals(3, persons.size());
		});
	}
