	@Test
	public void test_sql_custom_crud() {

		Person _person = doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person();
			person.setName( "John Doe" );
			entityManager.persist( person );
			return person;
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			Long postId = _person.getId();
			Person person = entityManager.find( Person.class, postId );
			assertNotNull(person);
			entityManager.remove( person );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			Long postId = _person.getId();
			Person person = entityManager.find( Person.class, postId );
			assertNull(person);
		} );
	}
