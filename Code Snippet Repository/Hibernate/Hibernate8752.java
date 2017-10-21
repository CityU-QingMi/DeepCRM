	@Before
	public void createTestData() {
		Session session = sessionFactory().openSession();
		session.beginTransaction();
		try {
			entity = new A();
			session.persist( entity );
		}
		finally {
			session.getTransaction().commit();
			session.close();
		}
	}
