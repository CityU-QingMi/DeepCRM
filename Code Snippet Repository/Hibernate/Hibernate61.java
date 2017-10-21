	@Test
	public void testLifecycle() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::collections-map-key-class-persist-example[]
			Person person = new Person();
			person.setId( 1L );
			person.getCallRegister().put( new MobilePhone( "01", "234", "567" ), 101 );
			person.getCallRegister().put( new MobilePhone( "01", "234", "789" ), 102 );

			entityManager.persist( person );
			//end::collections-map-key-class-persist-example[]
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::collections-map-key-class-fetch-example[]
			Person person = entityManager.find( Person.class, 1L );
			assertEquals( 2, person.getCallRegister().size() );

			assertEquals(
				Integer.valueOf( 101 ),
				person.getCallRegister().get( MobilePhone.fromString( "01-234-567" ) )
			);

			assertEquals(
				Integer.valueOf( 102 ),
				person.getCallRegister().get( MobilePhone.fromString( "01-234-789" ) )
			);
			//end::collections-map-key-class-fetch-example[]
		} );
	}
