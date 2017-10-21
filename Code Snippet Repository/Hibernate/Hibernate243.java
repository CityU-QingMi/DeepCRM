	@Test
	public void test_hql_collection_expressions_example_4() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Call call = entityManager.createQuery( "select c from Call c", Call.class).getResultList().get( 0 );
			Phone phone = call.getPhone();
			//tag::hql-collection-expressions-example[]

			// the above query can be re-written with member of
			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where :phone member of p.phones", Person.class )
			.setParameter( "phone", phone )
			.getResultList();
			//end::hql-collection-expressions-example[]
			assertEquals(1, persons.size());
		});
	}
