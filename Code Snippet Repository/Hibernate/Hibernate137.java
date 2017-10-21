	@Test
	public void test_hql_null_predicate_example_2() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-null-predicate-example[]

			// select all persons without a nickname
			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.nickName is null", Person.class )
			.getResultList();
			//end::hql-null-predicate-example[]
			assertEquals(2, persons.size());
		});
	}
