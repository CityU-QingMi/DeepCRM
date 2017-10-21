	@Test
	public void test_hql_null_predicate_example_1() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-null-predicate-example[]
			// select all persons with a nickname
			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.nickName is not null", Person.class )
			.getResultList();
			//end::hql-null-predicate-example[]
			assertEquals(1, persons.size());
		});
	}
