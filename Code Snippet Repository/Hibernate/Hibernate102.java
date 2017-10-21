	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Department department = new Department();
			department.id = 1L;
			entityManager.persist( department );

			Employee employee1 = new Employee();
			employee1.id = 1L;
			employee1.username = "user1";
			employee1.department = department;
			entityManager.persist( employee1 );

		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::fetching-direct-vs-query-direct-fetching-example[]
			Employee employee = entityManager.find( Employee.class, 1L );
			//end::fetching-direct-vs-query-direct-fetching-example[]
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::fetching-direct-vs-query-entity-query-example[]
			Employee employee = entityManager.createQuery(
					"select e " +
					"from Employee e " +
					"where e.id = :id", Employee.class)
			.setParameter( "id", 1L )
			.getSingleResult();
			//end::fetching-direct-vs-query-entity-query-example[]
		} );
	}
