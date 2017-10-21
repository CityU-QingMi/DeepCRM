	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person(  );
			person.setId( 1L );
			person.setName( "John Doe" );
			person.setCountry( "US" );
			person.setCity( "New York" );
			person.setCreatedOn( new Timestamp( System.currentTimeMillis() ) );
			entityManager.persist( person );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::locking-optimistic-lock-type-all-update-example[]
			Person person = entityManager.find( Person.class, 1L );
			person.setCity( "Washington D.C." );
			//end::locking-optimistic-lock-type-all-update-example[]
		} );
	}
