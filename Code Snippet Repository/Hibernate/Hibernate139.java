	@Test
	public void test_hql_like_predicate_example_2() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-like-predicate-example[]

			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.name not like 'Jo%'", Person.class )
			.getResultList();
			//end::hql-like-predicate-example[]
			assertEquals(2, persons.size());
		});
	}
