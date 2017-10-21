	@Test
	public void testSave() {
		Session s = openSession();
		Transaction transaction = s.beginTransaction();
		try {
			s.save( new Company() );
			s.getTransaction().commit();
		}
		finally {
			s.close();
		}
	}
