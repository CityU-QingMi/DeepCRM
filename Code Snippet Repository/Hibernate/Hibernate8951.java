	@Test
	public void testMergeTreeWithGeneratedId() {
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
		clearCounts();

		root.setDescription("The root node");
		child.setDescription("The child node");

		NumberedNode secondChild = new NumberedNode("second child");

		root.addChild(secondChild);

		s = openSession();
		tx = s.beginTransaction();
		s.merge(root);
		tx.commit();
		s.close();

		assertInsertCount(1);
		assertUpdateCount(2);

		cleanup();
	}
