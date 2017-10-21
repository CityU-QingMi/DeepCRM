	@Test
	public void test() {
		Person _person = doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person(  );
			person.setName( "John Doe" );
			entityManager.persist( person );

			return person;
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = entityManager.find( Person.class, _person.getId() );
			person.setName( person.getName().toUpperCase() );
		} );
	}
