	@Test
	public void testLifecycle() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			DateEvent dateEvent = new DateEvent( LocalDateTime.now() );
			dateEvent.id = 1L;
			entityManager.persist( dateEvent );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			DateEvent dateEvent = entityManager.find( DateEvent.class, 1L );
			assertNotNull(dateEvent.getTimestamp());
		} );
	}
