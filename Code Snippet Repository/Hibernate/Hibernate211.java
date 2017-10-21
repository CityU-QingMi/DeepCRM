	@Test
	public void test_hql_numeric_arithmetic_example_2() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-numeric-arithmetic-example[]

			// select clause date/time arithmetic operations
			Integer years = entityManager.createQuery(
				"select year( current_date() ) - year( p.createdOn ) " +
				"from Person p " +
				"where p.id = 1L", Integer.class )
			.getSingleResult();
			//end::hql-numeric-arithmetic-example[]
			assertTrue(years > 0);
		});
	}
