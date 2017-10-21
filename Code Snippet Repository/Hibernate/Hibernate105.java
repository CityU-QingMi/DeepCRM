	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			for ( long i = 0; i < 2; i++ ) {
				Department department = new Department();
				department.id = i + 1;
				department.name = String.format( "Department %d", department.id );
				entityManager.persist( department );

				for ( long j = 0; j < 3; j++ ) {
					Employee employee1 = new Employee();
					employee1.username = String.format( "user %d_%d", i, j );
					employee1.department = department;
					entityManager.persist( employee1 );
				}
			}
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::fetching-strategies-fetch-mode-subselect-example[]
			List<Department> departments = entityManager.createQuery(
				"select d " +
				"from Department d " +
				"where d.name like :token", Department.class )
			.setParameter( "token", "Department%" )
			.getResultList();

			log.infof( "Fetched %d Departments", departments.size());

			for (Department department : departments ) {
				assertEquals( 3, department.getEmployees().size() );
			}
			//end::fetching-strategies-fetch-mode-subselect-example[]
		} );
	}
