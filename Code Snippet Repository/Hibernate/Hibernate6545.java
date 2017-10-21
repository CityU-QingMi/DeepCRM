	public void testAutoboxing() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Bid bid = new Bid();
		bid.setId( new Integer( 2 ) );
		bid.setDescription( "My best one" );
		bid.setNote( Starred.OK );
		bid.setEditorsNote( Starred.GOOD );
		bid.setApproved( null );
		s.persist( bid );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		bid = (Bid) s.get( Bid.class, bid.getId() );
		assertEquals( null, bid.getApproved() );
		s.delete( bid );
		tx.commit();
		s.close();
	}
