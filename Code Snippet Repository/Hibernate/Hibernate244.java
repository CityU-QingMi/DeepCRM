	@Test
	public void test_hql_collection_expressions_example_5() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Call call = entityManager.createQuery( "select c from Call c", Call.class).getResultList().get( 0 );
			Phone phone = call.getPhone();
			//tag::hql-collection-expressions-example[]

			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where :phone = some elements ( p.phones )", Person.class )
			.setParameter( "phone", phone )
			.getResultList();
			//end::hql-collection-expressions-example[]
			assertEquals(1, persons.size());
		});
	}
