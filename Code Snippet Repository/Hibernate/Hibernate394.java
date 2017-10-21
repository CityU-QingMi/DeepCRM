	@Test
	public void persistTest() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::pc-cascade-persist-example[]
			Person person = new Person();
			person.setId( 1L );
			person.setName( "John Doe" );

			Phone phone = new Phone();
			phone.setId( 1L );
			phone.setNumber( "123-456-7890" );

			person.addPhone( phone );

			entityManager.persist( person );
			//end::pc-cascade-persist-example[]
		} );
	}
