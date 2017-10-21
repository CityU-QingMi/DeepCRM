	@Test
	public void testJPACallback() {
		Long personId = 1L;

		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person();
			person.id = personId;
			person.name = "John Doe";
			person.dateOfBirth = Timestamp.valueOf(LocalDateTime.of( 2000, 1, 1, 0, 0, 0 ));
			entityManager.persist( person );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = entityManager.find( Person.class, personId );
			assertTrue(person.age > 0);
		} );
	}
