	@Test
	public void testBuildLockRequest() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			log.info( "testBuildLockRequest" );
			Person person = new Person( "John Doe" );
			Phone home = new Phone( "123-456-7890" );
			Phone office = new Phone( "098-765-4321" );
			person.getPhones().add( home );
			person.getPhones().add( office );
			entityManager.persist( person );
			entityManager.flush();
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			Long id = 1L;
			//tag::locking-buildLockRequest-example[]
			Person person = entityManager.find( Person.class, id );
			Session session = entityManager.unwrap( Session.class );
			session
				.buildLockRequest( LockOptions.NONE )
				.setLockMode( LockMode.PESSIMISTIC_READ )
				.setTimeOut( LockOptions.NO_WAIT )
				.lock( person );
			//end::locking-buildLockRequest-example[]
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			Long id = 1L;
			//tag::locking-buildLockRequest-scope-example[]
			Person person = entityManager.find( Person.class, id );
			Session session = entityManager.unwrap( Session.class );
			session
				.buildLockRequest( LockOptions.NONE )
				.setLockMode( LockMode.PESSIMISTIC_READ )
				.setTimeOut( LockOptions.NO_WAIT )
				.setScope( true )
				.lock( person );
			//end::locking-buildLockRequest-scope-example[]
		} );

	}
