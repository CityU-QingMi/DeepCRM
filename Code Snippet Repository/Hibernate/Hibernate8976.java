	@Before
	public void before() {
		Session session = sessionFactory().openSession();
		session.getTransaction().begin();
		session.setCacheMode( CacheMode.IGNORE );
		for ( int i = 1; i <= 60; i++ ) {
			session.save( new SimpleEntity( i, "Entity #" + i ) );
		}
		session.getTransaction().commit();
		session.close();
	}
