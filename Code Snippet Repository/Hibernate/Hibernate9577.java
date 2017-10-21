	@Test
	public void test() {
		doInHibernate( this::sessionFactory, session -> {
			Event event = new Event();
			event.id = 1L;
			event.timeValue = new Time( 123 );
			event.timestampValue = new Timestamp( 456 );

			session.persist( event );
		} );
		doInHibernate( this::sessionFactory, session -> {
			Event event = session.find( Event.class, 1L );
			assertEquals(123, event.timeValue.getTime() % TimeUnit.DAYS.toMillis( 1 ));
			assertEquals(456, event.timestampValue.getTime() % TimeUnit.DAYS.toMillis( 1 ));
		} );
	}
