	@Test
	public void testHistoryOfChild1Node() {
		Node parent = new Node( "parent", parentId );
		Node child1 = new Node( "child1", child1Id );

		Node ver1 = getAuditReader().find( Node.class, child1Id, 1 );
		Assert.assertEquals( child1, ver1 );
		Assert.assertEquals( parent.getId(), ver1.getParent().getId() );
		Assert.assertEquals( parent.getData(), ver1.getParent().getData() );

		child1.setData( "child1 modified" );

		Node ver2 = getAuditReader().find( Node.class, child1Id, 2 );
		Assert.assertEquals( child1, ver2 );
		Assert.assertEquals( parent.getId(), ver2.getParent().getId() );
		Assert.assertEquals( parent.getData(), ver2.getParent().getData() );
	}
