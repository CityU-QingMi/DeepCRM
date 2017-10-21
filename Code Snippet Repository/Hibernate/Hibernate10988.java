	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1
		em.getTransaction().begin();
		Node parent = new Node( "parent", (Node) null );
		Node child1 = new Node( "child1", parent );
		Node child2 = new Node( "child2", parent );
		parent.getChildren().add( child1 );
		parent.getChildren().add( child2 );
		em.persist( parent );
		em.persist( child1 );
		em.persist( child2 );
		em.getTransaction().commit();

		parentId = parent.getId();
		child1Id = child1.getId();
		child2Id = child2.getId();

		// Revision 2
		em.getTransaction().begin();
		parent = em.find( Node.class, parent.getId() );
		parent.getChildren().get( 0 ).setData( "child1 modified" );
		em.getTransaction().commit();

		// Revision 3
		em.getTransaction().begin();
		child2 = em.find( Node.class, child2.getId() );
		em.remove( child2 );
		em.getTransaction().commit();
	}
