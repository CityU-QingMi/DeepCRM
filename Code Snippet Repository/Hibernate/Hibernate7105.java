	@Test
	public void testBatchAndOptimisticLocking() {
		doInHibernate( this::sessionFactory, session -> {
			Person person1 = new Person();
			person1.id = 1L;
			person1.name = "First";
			session.persist( person1 );

			Person person2 = new Person();
			person2.id = 2L;
			person2.name = "Second";
			session.persist( person2 );

			Person person3 = new Person();
			person3.id = 3L;
			person3.name = "Third";
			session.persist( person3 );

		} );

		try {
			doInHibernate( this::sessionFactory, session -> {
				List<Person> persons = session.createQuery( "select p from Person p").getResultList();

				for ( int i = 0; i < persons.size(); i++ ) {
					Person person = persons.get( i );
					person.name += " Person";

					if ( i == 1 ) {
						try {
							executorService.submit( () -> {
								doInHibernate( this::sessionFactory, _session -> {
									Person _person = _session.find( Person.class, person.id );
									_person.name += " Person is the new Boss!";
								} );
							} ).get();
						}
						catch (InterruptedException|ExecutionException e) {
							fail(e.getMessage());
						}
					}
				}
			} );
		}
		catch (Exception expected) {
			assertEquals( OptimisticLockException.class, expected.getClass());
		}
	}
