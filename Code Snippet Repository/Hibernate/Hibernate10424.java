	@Test
	@Priority(10)
	public void initData() {
		// add b/c key-pair to the map and save a entity.
		doInJPA( this::entityManagerFactory, entityManager -> {
			final A a = new A();

			final B b = new B();
			final C c = new C();
			entityManager.persist( b );
			entityManager.persist( c );

			a.getMap().put( b, c );
			entityManager.persist( a );

			this.a = a;
			this.b1 = b;
			this.c1 = c;
		} );

		// add a new b/c key-pair to the map
		doInJPA( this::entityManagerFactory, entityManager -> {
			final A a = entityManager.find( A.class, this.a.getId() );

			final B b = new B();
			final C c = new C();
			entityManager.persist( b );
			entityManager.persist( c );

			a.getMap().put( b, c );
			entityManager.merge( a );

			this.b2 = b;
			this.c2 = c;
		} );

		// Remove b1 from the map
		doInJPA( this::entityManagerFactory, entityManager -> {
			final A a = entityManager.find( A.class, this.a.getId() );
			a.getMap().remove( this.b1 );
			entityManager.merge( a );
		} );
	}
