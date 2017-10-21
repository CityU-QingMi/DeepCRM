	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person( );
			person.setId( 1L );
			person.setName( "John Doe" );
			person.setGender( Gender.MALE );
			entityManager.persist( person );
		} );
	}
