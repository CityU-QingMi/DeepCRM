	@Before
	public void createTestData() {
		Session session = openSession();
		session.beginTransaction();
		session.save( new Door( 1, "Front" ) );
		session.save( new Door( 2, "Back" ) );
		session.save( new Door( 3, "Garage" ) );
		session.save( new Door( 4, "French" ) );
		session.getTransaction().commit();
		session.close();
	}
