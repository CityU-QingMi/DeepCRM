	@Test
	public void testFindPreviousSibling() throws Exception {
		AST child1 = ASTUtil.create(factory,2, "child1");
		AST child2 = ASTUtil.create(factory,3, "child2");
		AST n = factory.make( new AST[] {
			ASTUtil.create(factory, 1, "parent"),
			child1,
			child2,
		});
		assertSame(child1,ASTUtil.findPreviousSibling( n,child2));
		Exception e = null;
		try {
			ASTUtil.findPreviousSibling(child1,null);
		}
		catch (Exception x) {
			e = x;
		}
		assertNotNull(e);
	}
