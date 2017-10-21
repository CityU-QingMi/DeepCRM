	@Test
	public void test() {
		Phone _phone = doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person(  );
			person.setName( "John Doe" );
			entityManager.persist( person );

			Phone phone = new Phone(  );
			phone.setNumber( "123-456-7890" );
			phone.setPerson( person );
			entityManager.persist( phone );

			return phone;
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = entityManager.find( Person.class, _phone.getPerson().getId() );
			person.setName( person.getName().toUpperCase() );

			Phone phone = entityManager.find( Phone.class, _phone.getId() );
			phone.setNumber( phone.getNumber().replace( "-", " ") );
		} );
	}
