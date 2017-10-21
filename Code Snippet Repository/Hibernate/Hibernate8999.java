	@Test
	public void testOneToOneLazyOrphanRemoval() {
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Initialize the data
		doInHibernate( this::sessionFactory, session -> {
			final PaintColor color = new PaintColor( 1, "Red" );
			final Engine engine = new Engine( 1, 275 );
			final Car car = new Car( 1, engine, color );

			session.save( engine );
			session.save( color );
			session.save( car );
		} );

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Test orphan removal for unidirectional relationship
		doInHibernate( this::sessionFactory, session -> {
			final Car car = session.find( Car.class, 1 );
			car.setEngine( null );
			session.update( car );
		} );

		doInHibernate( this::sessionFactory, session -> {
			final Car car = session.find( Car.class, 1 );
			assertNull( car.getEngine() );

			final Engine engine = session.find( Engine.class, 1 );
			assertNull( engine );
		} );

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Test orphan removal for bidirectional relationship
		doInHibernate( this::sessionFactory, session -> {
			final Car car = session.find( Car.class, 1 );
			car.setPaintColor( null );
			session.update( car );
		} );

		doInHibernate( this::sessionFactory, session -> {
			final Car car = session.find( Car.class, 1 );
			assertNull( car.getPaintColor() );

			final PaintColor color = session.find( PaintColor.class, 1 );
			assertNull( color );
		} );
	}
