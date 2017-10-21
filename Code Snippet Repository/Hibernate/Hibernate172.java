	@Test
	public void test_hql_select_simplest_example() {

        doInJPA( this::entityManagerFactory, entityManager -> {
            Session session = entityManager.unwrap( Session.class );
			List<Object> objects = session.createQuery(
				"from java.lang.Object" )
			.list();

			//tag::hql-select-simplest-example[]
			List<Person> persons = session.createQuery(
				"from Person" )
			.list();
			//end::hql-select-simplest-example[]
		});
	}
