	@Test
	public void testLifecycle() {
		Long personId = doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person( "ABC-123" );

			PersonDetails details = new PersonDetails();
			details.setPerson( person );

			entityManager.persist( person );
			entityManager.persist( details );

			return person.getId();
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			PersonDetails details = entityManager.find( PersonDetails.class, personId );
			Assert.assertNotNull( details );
		} );
	}
