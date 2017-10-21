	@Before
	public void setUp()
			throws Exception {
		doInHibernate( this::sessionFactory, session -> {
			for ( String title : GAME_TITLES ) {
				org.hibernate.jpa.test.query.NamedQueryTest.Game game = new org.hibernate.jpa.test.query.NamedQueryTest.Game(
						title );
				session.save( game );
			}
		} );
	}
