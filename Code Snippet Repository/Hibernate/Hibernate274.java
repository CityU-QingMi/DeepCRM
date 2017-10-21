	@Test
	public void testJPALockTimeout() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person( "John Doe" );
			entityManager.persist( person );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			log.info( "testJPALockTimeout" );
			Long id = 1L;
			//tag::locking-jpa-query-hints-timeout-example[]
			entityManager.find(
				Person.class, id, LockModeType.PESSIMISTIC_WRITE,
				Collections.singletonMap( "javax.persistence.lock.timeout", 200 )
			);
			//end::locking-jpa-query-hints-timeout-example[]
		} );
	}
