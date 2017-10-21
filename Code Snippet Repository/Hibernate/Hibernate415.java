	@Test
	public void test_sql_custom_crud() {

		Person _person = doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person();
			person.setName( "John Doe" );
			entityManager.persist( person );
			person.setImage( new byte[] {1, 2, 3} );
			return person;
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			Long postId = _person.getId();
			Person person = entityManager.find( Person.class, postId );
			assertArrayEquals(new byte[] {1, 2, 3}, person.getImage());
			entityManager.remove( person );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			Long postId = _person.getId();
			Person person = entityManager.find( Person.class, postId );
			assertNull(person);
		} );
	}
