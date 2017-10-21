	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Department department = new Department();
			department.id = 1L;
			entityManager.persist( department );

			Employee employee1 = new Employee();
			employee1.id = 1L;
			employee1.username = "user1";
			employee1.password = "3fabb4de8f1ee2e97d7793bab2db1116";
			employee1.accessLevel = 0;
			employee1.department = department;
			entityManager.persist( employee1 );

			Employee employee2 = new Employee();
			employee2.id = 2L;
			employee2.username = "user2";
			employee2.password = "3fabb4de8f1ee2e97d7793bab2db1116";
			employee2.accessLevel = 1;
			employee2.department = department;
			entityManager.persist( employee2 );

		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			String username = "user1";
			String password = "3fabb4de8f1ee2e97d7793bab2db1116";

			Session session = entityManager.unwrap( Session.class );

			//tag::fetching-strategies-dynamic-fetching-profile-example[]
			session.enableFetchProfile( "employee.projects" );
			Employee employee = session.bySimpleNaturalId( Employee.class ).load( username );
			//end::fetching-strategies-dynamic-fetching-profile-example[]
			assertNotNull(employee);
		} );

	}
