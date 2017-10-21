	@Test
	public void testClassLevelGenerator() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Store b = new Store();
		s.persist( b );
		tx.commit();
		s.close();
		assertNotNull( b.getId() );

		s = openSession();
		tx = s.beginTransaction();
		s.delete( s.get( Store.class, b.getId() ) );
		tx.commit();
		s.close();
	}
