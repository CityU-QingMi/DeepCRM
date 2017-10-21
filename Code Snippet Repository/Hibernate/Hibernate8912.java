	void check(boolean simplePropertyUpdated) {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Node node3 = (Node) s.get( Node.class, Integer.valueOf(3) );

		// fails with 2nd level cache enabled
		assertEquals( 1, node3.getParentNode().getId().intValue() );
		assertEquals( ( simplePropertyUpdated ? "node3-updated" : "node3" ), node3.getDescription() );
		assertTrue( node3.getSubNodes().isEmpty() );

		Node node1 = node3.getParentNode();
		assertNull( node1.getParentNode() );
		assertEquals( 2, node1.getSubNodes().size() );
		assertEquals( 2, ( ( Node ) node1.getSubNodes().get( 0 ) ).getId().intValue() );
		assertEquals( "node1", node1.getDescription() );

		Node node2 = ( Node ) node1.getSubNodes().get( 0 );
		assertSame( node1, node2.getParentNode() );
		assertTrue( node2.getSubNodes().isEmpty() );
		assertEquals( "node2", node2.getDescription() );

		tx.commit();
		s.close();
	}
