	@Test
	public void test_hql_collection_index_operator_example_2() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			String address = "Home address";
			//tag::hql-collection-index-operator-example[]

			// maps
			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.addresses[ 'HOME' ] = :address", Person.class )
			.setParameter( "address", address)
			.getResultList();
			//end::hql-collection-index-operator-example[]
			assertEquals(1, persons.size());
		});
	}
