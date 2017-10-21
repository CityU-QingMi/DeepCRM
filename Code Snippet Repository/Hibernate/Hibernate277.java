	@Test
	@RequiresDialect(Oracle8iDialect.class)
	public void testFollowOnLocking() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			log.info( "testBuildLockRequest" );
			Person person1 = new Person( "John Doe" );
			Person person2 = new Person( "Mrs. John Doe" );

			entityManager.persist( person1 );
			entityManager.persist( person2 );
			entityManager.flush();
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::locking-follow-on-example[]
			List<Person> persons = entityManager.createQuery(
				"select DISTINCT p from Person p", Person.class)
			.setLockMode( LockModeType.PESSIMISTIC_WRITE )
			.getResultList();
			//end::locking-follow-on-example[]
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::locking-follow-on-secondary-query-example[]
			List<Person> persons = entityManager.createQuery(
				"select DISTINCT p from Person p", Person.class)
			.getResultList();

			entityManager.createQuery(
				"select p.id from Person p where p in :persons")
			.setLockMode( LockModeType.PESSIMISTIC_WRITE )
			.setParameter( "persons", persons )
			.getResultList();
			//end::locking-follow-on-secondary-query-example[]
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::locking-follow-on-explicit-example[]
			List<Person> persons = entityManager.createQuery(
				"select p from Person p", Person.class)
			.setMaxResults( 10 )
			.unwrap( Query.class )
			.setLockOptions(
				new LockOptions( LockMode.PESSIMISTIC_WRITE )
					.setFollowOnLocking( false ) )
			.getResultList();
			//end::locking-follow-on-explicit-example[]
		} );
	}
