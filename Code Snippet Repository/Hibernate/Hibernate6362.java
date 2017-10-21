	@Test
	public void testSave() {
		Session s = openSession();
		s.getTransaction().begin();
		s.save( b );
		s.getTransaction().commit();
		s.close();

		check();
	}
