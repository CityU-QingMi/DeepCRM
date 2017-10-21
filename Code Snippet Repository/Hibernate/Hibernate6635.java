	@Test
	public void testSequenceGenerator() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Shoe b = new Shoe();
		s.persist( b );
		tx.commit();
		s.close();
		assertNotNull( b.getId() );

		s = openSession();
		tx = s.beginTransaction();
		s.delete( s.get( Shoe.class, b.getId() ) );
		tx.commit();
		s.close();
	}
