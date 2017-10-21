	@Before
	public void setUp() {
		doInHibernate( this::sessionFactory, session -> {
			final Role r1 = new Role( 1, "R1", false );
			final Role r2 = new Role( 2, "R2", false );
			session.save( r1 );
			session.save( r2 );

			final User user = new User( 1, "A", true, r1, r2 );
			session.save( user );
		} );
	}
