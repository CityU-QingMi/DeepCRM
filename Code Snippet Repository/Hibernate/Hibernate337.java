	@Test
	public void test() {
		//tag::mapping-generated-GeneratorType-persist-example[]
		CurrentUser.INSTANCE.logIn( "Alice" );

		doInJPA( this::entityManagerFactory, entityManager -> {

			Person person = new Person();
			person.setId( 1L );
			person.setFirstName( "John" );
			person.setLastName( "Doe" );

			entityManager.persist( person );
		} );

		CurrentUser.INSTANCE.logOut();
		//end::mapping-generated-GeneratorType-persist-example[]

		//tag::mapping-generated-GeneratorType-update-example[]
		CurrentUser.INSTANCE.logIn( "Bob" );

		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = entityManager.find( Person.class, 1L );
			person.setFirstName( "Mr. John" );
		} );

		CurrentUser.INSTANCE.logOut();
		//end::mapping-generated-GeneratorType-update-example[]
	}
