	@Test
	public void test_hql_explicit_inner_join_example_2() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-explicit-inner-join-example[]

			// same query but specifying join type as 'inner' explicitly
			List<Person> persons = entityManager.createQuery(
				"select distinct pr " +
				"from Person pr " +
				"inner join pr.phones ph " +
				"where ph.type = :phoneType", Person.class )
			.setParameter( "phoneType", PhoneType.MOBILE )
			.getResultList();
			//end::hql-explicit-inner-join-example[]
			assertEquals(1, persons.size());
		});
	}
