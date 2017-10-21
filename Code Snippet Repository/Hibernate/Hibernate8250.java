	@Test
	public void testUsage() {
		Session session = openSession();
		session.beginTransaction();
		Node root = new Node( "root" );
		session.save( root );
		assertNotNull( root.getId() );
		Node child = new Node( "child", root );
		session.save( child );
		assertNotNull( child.getId() );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		Node node = (Node) session.get( Node.class, root.getId() );
		assertNotNull( node );
		node = (Node) session.get( Node.class, child.getId() );
		assertNotNull( node );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		// test joining
		node = (Node) session.createQuery( "from Node n join fetch n.parent where n.parent is not null" ).uniqueResult();
		assertNotNull( node );
		assertNotNull( node.getParent() );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		session.delete( child );
		session.delete( root );
		session.getTransaction().commit();
		session.close();
	}
