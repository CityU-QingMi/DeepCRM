	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::mapping-generated-Generated-persist-example[]
			Person person = new Person();
			person.setId( 1L );
			person.setFirstName( "John" );
			person.setMiddleName1( "Flávio" );
			person.setMiddleName2( "André" );
			person.setMiddleName3( "Frederico" );
			person.setMiddleName4( "Rúben" );
			person.setMiddleName5( "Artur" );
			person.setLastName( "Doe" );

			entityManager.persist( person );
			entityManager.flush();

			assertEquals("John Flávio André Frederico Rúben Artur Doe", person.getFullName());
			//end::mapping-generated-Generated-persist-example[]
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::mapping-generated-Generated-update-example[]
			Person person = entityManager.find( Person.class, 1L );
			person.setLastName( "Doe Jr" );

			entityManager.flush();
			assertEquals("John Flávio André Frederico Rúben Artur Doe Jr", person.getFullName());
			//end::mapping-generated-Generated-update-example[]
		} );
	}
