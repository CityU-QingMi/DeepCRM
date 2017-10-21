	@Test
	public void test_sql_custom_crud() {

		Person _person = doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person();
			person.setName( "John Doe" );
			entityManager.persist( person );
			person.getPhones().add( "123-456-7890" );
			person.getPhones().add( "123-456-0987" );
			return person;
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			Long postId = _person.getId();
			Person person = entityManager.find( Person.class, postId );
			assertEquals( 2, person.getPhones().size() );
			person.getPhones().remove( 0 );
			person.setName( "Mr. John Doe" );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			Long postId = _person.getId();
			Person person = entityManager.find( Person.class, postId );
			assertEquals( 1, person.getPhones().size() );
			entityManager.remove( person );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			Long postId = _person.getId();
			Person person = entityManager.find( Person.class, postId );
			assertNull(person);
		} );
	}
