	@Test
	public void test_hql_like_predicate_escape_example() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-like-predicate-escape-example[]
			// find any person with a name starting with "Dr_"
			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.name like 'Dr|_%' escape '|'", Person.class )
			.getResultList();
			//end::hql-like-predicate-escape-example[]
			assertEquals(1, persons.size());
		});
	}
