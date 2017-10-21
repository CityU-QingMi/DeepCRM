	@Test
	public void testCreateTreeWithGeneratedId() {
		clearCounts();

		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		NumberedNode root = new NumberedNode( "root" );
		NumberedNode child = new NumberedNode( "child" );
		root.addChild( child );
		em.persist( root );
		em.getTransaction().commit();
		em.close();

		assertInsertCount( 2 );
		assertUpdateCount( 0 );

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		root = em.find( NumberedNode.class, root.getId() );
		NumberedNode child2 = new NumberedNode( "child2" );
		root.addChild( child2 );
		em.getTransaction().commit();
		em.close();

		assertInsertCount( 3 );
		assertUpdateCount( 0 );
	}
