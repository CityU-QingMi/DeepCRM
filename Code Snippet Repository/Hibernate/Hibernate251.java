	@Test
	public void test_collection_index_operator_example_1() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-collection-index-operator-example[]
			// indexed lists
			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.phones[ 0 ].type = 'LAND_LINE'", Person.class )
			.getResultList();
			//end::hql-collection-index-operator-example[]
			assertEquals(1, persons.size());
		});
	}
