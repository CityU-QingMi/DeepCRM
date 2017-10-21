	@Test
	public void testCreateTree() {

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
		assertUpdateCount( 0 );

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		root = em.find( Node.class, "root" );
		Node child2 = new Node( "child2" );
		root.addChild( child2 );
		em.getTransaction().commit();
		em.close();

		assertInsertCount( 3 );
		assertUpdateCount( 0 );
	}
