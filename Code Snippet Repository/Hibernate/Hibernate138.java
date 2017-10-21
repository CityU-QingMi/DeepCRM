	@Test
	public void test_hql_like_predicate_example_1() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-like-predicate-example[]
			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like 'Jo%'", Person.class )
			.getResultList();
			//end::hql-like-predicate-example[]
			assertEquals(1, persons.size());
		});
	}
