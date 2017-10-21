	@Test
	public void testImplicitCompositeFk() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Node n1 = new Node();
		n1.setDescription( "Parent" );
		NodePk n1pk = new NodePk();
		n1pk.setLevel( 1 );
		n1pk.setName( "Root" );
		n1.setId( n1pk );
		Node n2 = new Node();
		NodePk n2pk = new NodePk();
		n2pk.setLevel( 2 );
		n2pk.setName( "Level 1: A" );
		n2.setParent( n1 );
		n2.setId( n2pk );
		s.persist( n2 );
		tx.commit();

		s = openSession();
		tx = s.beginTransaction();
		n2 = (Node) s.get( Node.class, n2pk );
		assertNotNull( n2 );
		assertNotNull( n2.getParent() );
		assertEquals( 1, n2.getParent().getId().getLevel() );
		tx.commit();
		s.close();
	}
