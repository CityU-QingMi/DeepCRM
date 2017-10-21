	@Test
	public void test_hql_explicit_inner_join_example_1() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-explicit-inner-join-example[]
			List<Person> persons = entityManager.createQuery(
				"select distinct pr " +
				"from Person pr " +
				"join pr.phones ph " +
				"where ph.type = :phoneType", Person.class )
			.setParameter( "phoneType", PhoneType.MOBILE )
			.getResultList();
			//end::hql-explicit-inner-join-example[]
			assertEquals(1, persons.size());
		});
	}
