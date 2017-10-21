	@Test
	public void testMergeTree() {
		clearCounts();

		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Node root = new Node( "root" );
		Node child = new Node( "child" );
		root.addChild( child );
		em.persist( root );
		em.getTransaction().commit();
		em.close();

		assertInsertCount( 2 );
		clearCounts();

		root.setDescription( "The root node" );
		child.setDescription( "The child node" );

		Node secondChild = new Node( "second child" );

		root.addChild( secondChild );

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.merge( root );
		em.getTransaction().commit();
		em.close();

		assertInsertCount( 1 );
		assertUpdateCount( 2 );
	}
