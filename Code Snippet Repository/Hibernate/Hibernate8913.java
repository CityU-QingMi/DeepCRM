	@Override
	void check(boolean simplePropertyUpdated) {
		super.check( simplePropertyUpdated );
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Node node1 = ( Node ) s.get( Node.class, Integer.valueOf( 1 ) );
		Node node2 = ( Node ) s.get( Node.class, Integer.valueOf( 2 ) );
		Node node3 = ( Node ) s.get( Node.class, Integer.valueOf( 3 ) );
		assertEquals( 1, node1.getVersion() );
		assertEquals( 1, node2.getVersion() );
		assertEquals( 1, node3.getVersion() );
		tx.commit();
		s.close();
	}
