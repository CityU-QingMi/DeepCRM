	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::associations-not-found-persist-example[]
			City _NewYork = new City();
			_NewYork.setName( "New York" );
			entityManager.persist( _NewYork );

			Person person = new Person();
			person.setId( 1L );
			person.setName( "John Doe" );
			person.setCityName( "New York" );
			entityManager.persist( person );
			//end::associations-not-found-persist-example[]
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::associations-not-found-find-example[]
			Person person = entityManager.find( Person.class, 1L );
			assertEquals( "New York", person.getCity().getName() );
			//end::associations-not-found-find-example[]

			//tag::associations-not-found-non-existing-persist-example[]
			person.setCityName( "Atlantis" );
			//end::associations-not-found-non-existing-persist-example[]

		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::associations-not-found-non-existing-find-example[]
			Person person = entityManager.find( Person.class, 1L );

			assertEquals( "Atlantis", person.getCityName() );
			assertNull( null, person.getCity() );
			//end::associations-not-found-non-existing-find-example[]
		} );
	}
