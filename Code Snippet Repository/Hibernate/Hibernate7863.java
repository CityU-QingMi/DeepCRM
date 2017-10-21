	@Test
	public void testForeignKeyNameUnicity() {
		Session session = openSession();
		Transaction transaction = session.beginTransaction();

		LocalDateEvent event1 = new LocalDateEvent();
		event1.startDate = LocalDate.of(1, 1, 1);
		session.persist(event1);

		LocalDateEvent event2 = new LocalDateEvent();
		event2.startDate = LocalDate.of(1, 1, 2);
		session.persist(event2);

		LocalDateEvent event3 = new LocalDateEvent();
		event3.startDate = LocalDate.of(1, 1, 3);
		session.persist(event3);

		UserEvents userEvents = new UserEvents();
		session.persist( userEvents );
		userEvents.getEvents().add( event1 );
		session.flush();
		userEvents.getEvents().add( event2 );
		session.flush();


		ApplicationEvents applicationEvents = new ApplicationEvents();
		session.persist( applicationEvents );
		applicationEvents.getEvents().add( event3 );

		transaction.commit();
		session.close();

		session = openSession();
		transaction = session.beginTransaction();

		assertEquals(2, session.get( UserEvents.class, userEvents.id ).getEvents().size());
		assertEquals(1, session.get( ApplicationEvents.class, applicationEvents.id ).getEvents().size());

		transaction.commit();
		session.close();
	}
