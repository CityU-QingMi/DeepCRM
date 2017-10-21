	@Test
	@RequiresDialect(H2Dialect.class)
	@RequiresDialect(PostgreSQL81Dialect.class)
	@RequiresDialect(MySQL5Dialect.class)
	public void test_hql_relational_comparisons_example_3() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-relational-comparisons-example[]

			// datetime comparison
			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.createdOn > '1950-01-01' ", Person.class )
			.getResultList();
			//end::hql-relational-comparisons-example[]
			assertEquals(2, persons.size());
		});
	}
