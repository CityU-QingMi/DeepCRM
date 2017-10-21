	@Test
	public void testRemove() {
		try {
			final Long personId = doInJPA( this::entityManagerFactory, entityManager -> {
				Person person1 = new Person();
				Person person2 = new Person();

				Address address1 = new Address( "12th Avenue", "12A" );
				Address address2 = new Address( "18th Avenue", "18B" );

				person1.getAddresses().add( address1 );
				person1.getAddresses().add( address2 );

				person2.getAddresses().add( address1 );

				entityManager.persist( person1 );
				entityManager.persist( person2 );

				return person1.id;
			} );
			doInJPA( this::entityManagerFactory, entityManager -> {

				Person person1 = entityManager.find( Person.class, personId );
				entityManager.remove( person1 );
			} );
		}
		catch (Exception expected) {
			log.error( "Expected", expected );
		}
	}
