	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			for ( long i = 0; i < 10; i++ ) {
				Department department = new Department();
				department.id = i;
				entityManager.persist( department );

				for ( int j = 0; j < Math.random() * 5; j++ ) {
					Employee employee = new Employee();
					employee.id = (i * 5) + j;
					employee.name = String.format( "John %d", employee.getId() );
					employee.department = department;
					entityManager.persist( employee );
					department.employees.add( employee );
				}
				entityManager.flush();
			}
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::fetching-batch-fetching-example[]
			List<Department> departments = entityManager.createQuery(
				"select d " +
				"from Department d " +
				"inner join d.employees e " +
				"where e.name like 'John%'", Department.class)
			.getResultList();

			for ( Department department : departments ) {
				log.infof(
					"Department %d has {} employees",
					department.getId(),
					department.getEmployees().size()
				);
			}
			//end::fetching-batch-fetching-example[]
		} );
	}
