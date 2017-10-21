	@Test
	public void test() {
		final DateEvent _dateEvent = doInJPA( this::entityManagerFactory, entityManager -> {
			DateEvent dateEvent = new DateEvent();
			dateEvent.setCreatedOn( Instant.from( DateTimeFormatter.ISO_INSTANT.parse( "2016-10-13T06:40:18.745Z" ) ) );
			entityManager.persist( dateEvent );

			return dateEvent;
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			DateEvent dateEvent = entityManager.unwrap( Session.class )
			.createQuery(
				"select de " +
				"from DateEvent de " +
				"where de.createdOn = '2016-10-13T06:40:18.745Z' ", DateEvent.class )
			.getSingleResult();

			assertNotNull(dateEvent);
			assertEquals(_dateEvent.getId(), dateEvent.getId());
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			DateEvent dateEvent = entityManager.unwrap( Session.class )
			.createQuery(
				"select de " +
				"from DateEvent de " +
				"where de.createdOn = :createdOn ", DateEvent.class )
			.setParameter( "createdOn", Instant.from( DateTimeFormatter.ISO_INSTANT.parse( "2016-10-13T06:40:18.745Z" ) ) )
			.getSingleResult();

			assertNotNull(dateEvent);
			assertEquals(_dateEvent.getId(), dateEvent.getId());
		} );
	}
