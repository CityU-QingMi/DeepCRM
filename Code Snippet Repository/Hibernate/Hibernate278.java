	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Phone phone = new Phone();
			phone.setId( 1L );
			phone.setNumber( "123-456-7890" );
			entityManager.persist( phone );

			return phone;
		} );

		//tag::locking-optimistic-exclude-attribute-example[]
		doInJPA( this::entityManagerFactory, entityManager -> {
			Phone phone = entityManager.find( Phone.class, 1L );
			phone.setNumber( "+123-456-7890" );

			doInJPA( this::entityManagerFactory, _entityManager -> {
				Phone _phone = _entityManager.find( Phone.class, 1L );
				_phone.incrementCallCount();

				log.info( "Bob changes the Phone call count" );
			} );

			log.info( "Alice changes the Phone number" );
		} );
		//end::locking-optimistic-exclude-attribute-example[]
	}
