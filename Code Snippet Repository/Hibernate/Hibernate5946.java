	@Test
	public void testLifecycle() {
		Person _person = doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person();
			person.id = 1L;
			person.name = "John Doe";
			entityManager.persist( person );

			assertTrue(entityManager.contains( person ));

			return person;
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			assertFalse(entityManager.contains( _person ));

			Person person = entityManager.find( Person.class, 1L );

			assertTrue(entityManager.contains( person ));
		} );
	}
