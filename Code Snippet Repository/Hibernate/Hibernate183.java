	@Test
	public void test_hql_select_simplest_jpql_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-select-simplest-jpql-example[]
			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p", Person.class )
			.getResultList();
			//end::hql-select-simplest-jpql-example[]
		});
	}
