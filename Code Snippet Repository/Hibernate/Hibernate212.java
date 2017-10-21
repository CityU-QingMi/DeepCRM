	@Test
	public void test_hql_numeric_arithmetic_example_3() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-numeric-arithmetic-example[]

			// where clause arithmetic operations
			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"where year( current_date() ) - year( p.createdOn ) > 1", Person.class )
			.getResultList();
			//end::hql-numeric-arithmetic-example[]
			assertTrue(persons.size() > 0);
		});
	}
