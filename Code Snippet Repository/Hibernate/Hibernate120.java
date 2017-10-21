	@Test
	public void test_hql_explicit_outer_join_example_2() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-explicit-outer-join-example[]

			// functionally the same query but using the 'left outer' phrase
			List<Person> persons = entityManager.createQuery(
				"select distinct pr " +
				"from Person pr " +
				"left outer join pr.phones ph " +
				"where ph is null " +
				"   or ph.type = :phoneType", Person.class )
			.setParameter( "phoneType", PhoneType.LAND_LINE )
			.getResultList();
			//end::hql-explicit-outer-join-example[]
			assertEquals(2, persons.size());
		});
	}
