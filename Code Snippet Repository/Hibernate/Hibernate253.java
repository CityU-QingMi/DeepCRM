	@Test
	public void test_hql_collection_index_operator_example_3() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-collection-index-operator-example[]

			//max index in list
			List<Person> persons = entityManager.createQuery(
				"select pr " +
				"from Person pr " +
				"where pr.phones[ maxindex(pr.phones) ].type = 'LAND_LINE'", Person.class )
			.getResultList();
			//end::hql-collection-index-operator-example[]
			assertEquals(1, persons.size());
		});
	}
