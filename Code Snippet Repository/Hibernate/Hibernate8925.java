	@Test
	public void testCreateTree() {
		clearCounts();

		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Node root = new Node("root");
		Node child = new Node("child");
		root.addChild(child);
		s.persist(root);
		tx.commit();
		s.close();

		assertInsertCount(2);
		assertUpdateCount(0);

		s = openSession();
		tx = s.beginTransaction();
		System.out.println("getting");
		root = (Node) s.get(Node.class, "root");
		Node child2 = new Node("child2");
		root.addChild(child2);
		System.out.println("committing");
		tx.commit();
		s.close();

		assertInsertCount(3);
		assertUpdateCount(0);
	}
