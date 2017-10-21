	@Test
	public void test_hql_explicit_outer_join_example_1() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-explicit-outer-join-example[]
			List<Person> persons = entityManager.createQuery(
				"select distinct pr " +
				"from Person pr " +
				"left join pr.phones ph " +
				"where ph is null " +
				"   or ph.type = :phoneType", Person.class )
			.setParameter( "phoneType", PhoneType.LAND_LINE )
			.getResultList();
			//end::hql-explicit-outer-join-example[]
			assertEquals(2, persons.size());
		});
	}
