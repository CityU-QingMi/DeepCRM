	@Test
	public void testSaveOrUpdate() {
		Session s = openSession();
		s.getTransaction().begin();
		s.saveOrUpdate( b );
		s.getTransaction().commit();
		s.close();

		check();
	}
