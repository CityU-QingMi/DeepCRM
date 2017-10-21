	@Test
	public void testSaveOrUpdateTree() {
		clearCounts();

		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Node root = new Node( "root" );
		Node child = new Node( "child" );
		root.addChild( child );
		s.saveOrUpdate( root );
		tx.commit();
		s.close();

		assertInsertCount( 2 );
		clearCounts();

		root.setDescription( "The root node" );
		child.setDescription( "The child node" );

		Node secondChild = new Node( "second child" );

		root.addChild( secondChild );

		s = openSession();
		tx = s.beginTransaction();
		s.saveOrUpdate( root );
		tx.commit();
		s.close();

		assertInsertCount( 1 );
		assertUpdateCount( 2 );

		s = openSession();
		tx = s.beginTransaction();
		s.createQuery( "delete from Node where parent is not null" ).executeUpdate();
		s.createQuery( "delete from Node" ).executeUpdate();
		tx.commit();
		s.close();
	}
