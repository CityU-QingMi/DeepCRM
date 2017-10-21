	@Test
	public void test() {
		Long addressId = doInHibernate( this::sessionFactory, session -> {
			Event event = new Event();
			event.setId( 1L );
			event.setName( "Hibernate ORM presentation" );
			Point<C2D> pnt = point( crs, c( 10, 5 ) );
			event.setLocation( pnt );
			session.persist( event );
			return event.getId();
		} );

		doInHibernate( this::sessionFactory, session -> {
			List<Event> events = session.createQuery(
					"select e " +
							"from Event e " +
							"where buffer(:window, 100) is not null", Event.class )
					.setParameter( "window", window )
					.getResultList();

			assertEquals( 1, events.size() );

		} );
	}
