	@Test
	public void testInterface() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Race r = new Race();
		r.setId( new Integer( 1 ) );
		r.setLength( new Long( 3 ) );
		s.persist( r );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		r = (Race) s.get( Race.class, r.getId() );
		assertEquals( new Long( 3 ), r.getLength() );
		tx.commit();
		s.close();

	}
