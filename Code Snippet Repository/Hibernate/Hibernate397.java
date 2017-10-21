	@Test
	public void refreshTest() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person();
			person.setId( 1L );
			person.setName( "John Doe" );

			Phone phone = new Phone();
			phone.setId( 1L );
			phone.setNumber( "123-456-7890" );

			person.addPhone( phone );
			entityManager.persist( person );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {

			//tag::pc-cascade-replicate-example[]
			Person person = new Person();
			person.setId( 1L );
			person.setName( "John Doe Sr." );

			Phone phone = new Phone();
			phone.setId( 1L );
			phone.setNumber( "(01) 123-456-7890" );
			person.addPhone( phone );

			entityManager.unwrap( Session.class ).replicate( person, ReplicationMode.OVERWRITE );
			//end::pc-cascade-replicate-example[]
		} );
	}
