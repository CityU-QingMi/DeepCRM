	@Test
	public void test_hql_all_subquery_comparison_qualifier_example() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-all-subquery-comparison-qualifier-example[]
			// select all persons with all calls shorter than 50 seconds
			List<Person> persons = entityManager.createQuery(
				"select distinct p.person " +
				"from Phone p " +
				"join p.calls c " +
				"where 50 > all ( " +
				"	select duration" +
				"	from Call" +
				"	where phone = p " +
				") ", Person.class )
			.getResultList();
			//end::hql-all-subquery-comparison-qualifier-example[]
			assertEquals(1, persons.size());
		});
	}
