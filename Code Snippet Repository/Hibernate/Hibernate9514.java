	@Before
	public void before() {
		Session session = openSession();
		session.getTransaction().begin();

		Name chris = new Name();
		chris.setId( 1 );
		chris.setName( "chris" );
		Value cat = new Value();
		cat.setId(1);
		cat.setName( chris );
		cat.setValue( "cat" );
		Value canary = new Value();
		canary.setId( 2 );
		canary.setName( chris );
		canary.setValue( "canary" );

		session.persist( chris );
		session.persist( cat );
		session.persist( canary );

		Name sam = new Name();
		sam.setId(2);
		sam.setName( "sam" );
		Value seal = new Value();
		seal.setId( 3 );
		seal.setName( sam );
		seal.setValue( "seal" );
		Value snake = new Value();
		snake.setId( 4 );
		snake.setName( sam );
		snake.setValue( "snake" );

		session.persist( sam );
		session.persist(seal);
		session.persist( snake );

		session.getTransaction().commit();
		session.close();
	}
