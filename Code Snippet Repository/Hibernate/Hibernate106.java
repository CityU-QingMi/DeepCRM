	@Test
	public void test() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::fetching-LazyCollection-persist-example[]
			Department department = new Department();
			department.setId( 1L );
			entityManager.persist( department );

			for (long i = 1; i <= 3; i++ ) {
				Employee employee = new Employee();
				employee.setId( i );
				employee.setUsername( String.format( "user_%d", i ) );
				department.addEmployee(employee);
			}
			//end::fetching-LazyCollection-persist-example[]
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::fetching-LazyCollection-select-example[]
			Department department = entityManager.find(Department.class, 1L);

			int employeeCount = department.getEmployees().size();

			for(int i = 0; i < employeeCount; i++ ) {
				log.infof( "Fetched employee: %s", department.getEmployees().get( i ).getUsername());
			}
			//end::fetching-LazyCollection-select-example[]
		} );
	}
