	@Test
	public void testPersist() {
		Session s = openSession();
		s.getTransaction().begin();
		s.persist( b );
		s.getTransaction().commit();
		s.close();

		check();
	}
