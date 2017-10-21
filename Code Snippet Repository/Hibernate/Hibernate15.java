	@Test
	public void testLifecycle() {
		PersonAddress _personAddress = doInJPA( this::entityManagerFactory, entityManager -> {
			Person person1 = new Person( "ABC-123" );
			Person person2 = new Person( "DEF-456" );

			Address address1 = new Address( "12th Avenue", "12A", "4005A" );
			Address address2 = new Address( "18th Avenue", "18B", "4007B" );

			entityManager.persist( person1 );
			entityManager.persist( person2 );

			entityManager.persist( address1 );
			entityManager.persist( address2 );

			PersonAddress personAddress = new PersonAddress( person1, address1 );
			entityManager.persist( personAddress );
			return personAddress;
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			Address address = entityManager.createQuery( "from Address", Address.class ).getResultList().get( 0 );
			Person person = entityManager.createQuery( "from Person", Person.class ).getResultList().get( 0 );
			PersonAddress personAddress = entityManager.find(
					PersonAddress.class,
					new PersonAddress( person, address )
			);
		} );
	}
