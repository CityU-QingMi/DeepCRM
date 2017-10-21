	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person();
			person.setId( 1L );
			person.setName( "John Doe" );
			entityManager.persist( person );

			Phone phone = new Phone();
			phone.setId( 1L );
			phone.setNumber( "123-456-7890" );
			phone.setOwner( person );
			entityManager.persist( phone );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::pc-cascade-on-delete-example[]
			Person person = entityManager.find( Person.class, 1L );
			entityManager.remove( person );
			//end::pc-cascade-on-delete-example[]
		} );
	}
