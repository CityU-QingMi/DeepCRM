	@Test
	public void testOneToOneEagerOrphanRemoval() {
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Initialize the data
		doInJPA( this::entityManagerFactory, entityManager -> {
			final PaintColor color = new PaintColor( 1, "Red" );
			final Engine engine = new Engine( 1, 275 );
			final Car car = new Car( 1, engine, color );

			entityManager.persist( engine );
			entityManager.persist( color );
			entityManager.persist( car );
		} );

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Test orphan removal for unidirectional relationship
		doInJPA( this::entityManagerFactory, entityManager -> {
			final Car car = entityManager.find( Car.class, 1 );
			car.setEngine( null );
			entityManager.merge( car );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			final Car car = entityManager.find( Car.class, 1 );
			assertNull( car.getEngine() );

			final Engine engine = entityManager.find( Engine.class, 1 );
			assertNull( engine );
		} );

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Test orphan removal for bidirectional relationship
		doInJPA( this::entityManagerFactory, entityManager -> {
			final Car car = entityManager.find( Car.class, 1 );
			car.setPaintColor( null );
			entityManager.merge( car );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			final Car car = entityManager.find( Car.class, 1 );
			assertNull( car.getPaintColor() );

			final PaintColor color = entityManager.find( PaintColor.class, 1 );
			assertNull( color );
		} );
	}
