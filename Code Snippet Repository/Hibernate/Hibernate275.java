	@Test
	public void testJPALockScope() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person( "John Doe" );
			entityManager.persist( person );
			Phone home = new Phone( "123-456-7890" );
			Phone office = new Phone( "098-765-4321" );
			person.getPhones().add( home );
			person.getPhones().add( office );
			entityManager.persist( person );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			log.info( "testJPALockScope" );
			Long id = 1L;
			//tag::locking-jpa-query-hints-scope-example[]
			Person person = entityManager.find(
				Person.class, id, LockModeType.PESSIMISTIC_WRITE,
				Collections.singletonMap(
					"javax.persistence.lock.scope",
					PessimisticLockScope.EXTENDED )
			);
			//end::locking-jpa-query-hints-scope-example[]
			assertEquals( 2, person.getPhones().size() );
		} );
	}
