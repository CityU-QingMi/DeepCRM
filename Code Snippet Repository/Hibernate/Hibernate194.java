	@Test
	public void hql_select_simplest_jpql_fqn_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-select-simplest-jpql-fqn-example[]
			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from org.hibernate.userguide.model.Person p", Person.class )
			.getResultList();
			//end::hql-select-simplest-jpql-fqn-example[]
		});
	}
