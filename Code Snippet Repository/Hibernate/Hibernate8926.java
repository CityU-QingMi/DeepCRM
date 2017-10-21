	@Test
	public void testCreateTreeWithGeneratedId() {
		clearCounts();

		Session s = openSession();
		Transaction tx = s.beginTransaction();
		NumberedNode root = new NumberedNode("root");
		NumberedNode child = new NumberedNode("child");
		root.addChild(child);
		s.persist(root);
		tx.commit();
		s.close();

		assertInsertCount(2);
		assertUpdateCount(0);

		s = openSession();
		tx = s.beginTransaction();
		root = (NumberedNode) s.get( NumberedNode.class, Long.valueOf( root.getId() ) );
		NumberedNode child2 = new NumberedNode("child2");
		root.addChild(child2);
		tx.commit();
		s.close();

		assertInsertCount(3);
		assertUpdateCount(0);
	}
