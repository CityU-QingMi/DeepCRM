	void init() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();

		Node node1 = new Node( 1, "node1" );
		Node node2 = new Node( 2, "node2" );
		Node node3 = new Node( 3, "node3" );

		node1.addSubNode( node2 );
		node2.addSubNode( node3 );

		s.save(node1);

		tx.commit();
		s.close();
	}
