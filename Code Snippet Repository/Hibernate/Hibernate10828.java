	@Test
	public void testAuditChildTableAlias() {
		Parent parent = new Parent( "parent", parentId );
		Child child = new Child( "child", childId );

		Parent ver1 = getAuditReader().find( Parent.class, parentId, 1 );

		Assert.assertEquals( parent, ver1 );
		Assert.assertEquals( TestTools.makeSet( child ), ver1.getCollection() );
	}
