	@Test
	public void testHistoryOfParentNode() {
		Node parent = new Node( "parent", parentId );
		Node child1 = new Node( "child1", child1Id );
		Node child2 = new Node( "child2", child2Id );

		Node ver1 = getAuditReader().find( Node.class, parentId, 1 );
		Assert.assertEquals( parent, ver1 );
		Assert.assertTrue( TestTools.checkCollection( ver1.getChildren(), child1, child2 ) );

		child1.setData( "child1 modified" );

		Node ver2 = getAuditReader().find( Node.class, parentId, 2 );
		Assert.assertEquals( parent, ver2 );
		Assert.assertTrue( TestTools.checkCollection( ver2.getChildren(), child1, child2 ) );

		Node ver3 = getAuditReader().find( Node.class, parentId, 3 );
		Assert.assertEquals( parent, ver3 );
		Assert.assertTrue( TestTools.checkCollection( ver3.getChildren(), child1 ) );
	}
