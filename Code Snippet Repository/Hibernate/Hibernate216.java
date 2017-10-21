	@Test
	public void test_hql_multiple_same_root_reference_jpql_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-multiple-same-root-reference-jpql-example[]
			List<Person> persons = entityManager.createQuery(
				"select distinct pr1 " +
				"from Person pr1, Person pr2 " +
				"where pr1.id <> pr2.id " +
				"  and pr1.address = pr2.address " +
				"  and pr1.createdOn < pr2.createdOn", Person.class )
			.getResultList();
			//end::hql-multiple-same-root-reference-jpql-example[]
			assertEquals(1, persons.size());
		});
	}
