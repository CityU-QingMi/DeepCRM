	@Test
	@RequiresDialect(H2Dialect.class)
	@RequiresDialect(PostgreSQL81Dialect.class)
	@RequiresDialect(MySQL5Dialect.class)
	public void test_hql_between_predicate_example_2() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-between-predicate-example[]

			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where p.createdOn between '1999-01-01' and '2001-01-02'", Person.class )
			.getResultList();
			//end::hql-between-predicate-example[]
			assertEquals(2, persons.size());
		});
	}
