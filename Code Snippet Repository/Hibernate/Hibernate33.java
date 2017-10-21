	@Test
	public void testLifecycle() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Phone phone = new Phone( "123-456-7890" );
			PhoneDetails details = new PhoneDetails( "T-Mobile", "GSM" );

			phone.setDetails( details );
			entityManager.persist( phone );
			entityManager.persist( details );
		} );
	}
