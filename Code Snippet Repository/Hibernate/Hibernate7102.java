	@Test
	public void testParentsFirstIterator() throws Exception {
		AST[] tree = new AST[4];
		AST grandparent = tree[0] = ASTUtil.create( factory, 1, "grandparent" );
		AST parent = tree[1] = ASTUtil.create( factory, 2, "parent" );
		AST child = tree[2] = ASTUtil.create( factory, 3, "child" );
		AST baby = tree[3] = ASTUtil.create( factory, 4, "baby" );
		AST t = ASTUtil.createTree( factory, tree );
		AST brother = ASTUtil.create( factory, 10, "brother" );
		child.setNextSibling( brother );
		AST sister = ASTUtil.create( factory, 11, "sister" );
		brother.setNextSibling( sister );
		AST uncle = factory.make( new AST[]{
			factory.create( 20, "uncle" ),
			factory.create( 21, "cousin1" ),
			factory.create( 22, "cousin2" ),
			factory.create( 23, "cousin3" )} );
		parent.setNextSibling( uncle );
		System.out.println( t.toStringTree() );

		System.out.println( "--- ASTParentsFirstIterator ---" );
		ASTParentsFirstIterator iter = new ASTParentsFirstIterator( t );
		int count = 0;
		while ( iter.hasNext() ) {
			AST n = iter.nextNode();
			count++;
			System.out.println( n );
		}
		assertEquals( 10, count );

		System.out.println( "--- ASTIterator ---" );
		ASTIterator iter2 = new ASTIterator( t );
		int count2 = 0;
		while ( iter2.hasNext() ) {
			AST n = iter2.nextNode();
			count2++;
			System.out.println( n );
		}
		assertEquals( 10, count2 );

		System.out.println( "--- ASTParentsFirstIterator (parent) ---" );
		ASTParentsFirstIterator iter3 = new ASTParentsFirstIterator( parent );
		int count3 = 0;
		while ( iter3.hasNext() ) {
			AST n = iter3.nextNode();
			count3++;
			System.out.println( n );
		}
		assertEquals( 5, count3 );
	}
