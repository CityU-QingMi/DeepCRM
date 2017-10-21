	@Test
	public void testMethodLevelGenerator() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Department b = new Department();
		s.persist( b );
		tx.commit();
		s.close();
		assertNotNull( b.getId() );

		s = openSession();
		tx = s.beginTransaction();
		s.delete( s.get( Department.class, b.getId() ) );
		tx.commit();
		s.close();
	}
