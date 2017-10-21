	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			for ( long i = 0; i < 2; i++ ) {
				Department department = new Department();
				department.id = i + 1;
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
			//tag::fetching-strategies-fetch-mode-join-example[]
			Department department = entityManager.find( Department.class, 1L );

			log.infof( "Fetched department: %s", department.getId());

			assertEquals( 3, department.getEmployees().size() );
			//end::fetching-strategies-fetch-mode-join-example[]
		} );
	}
