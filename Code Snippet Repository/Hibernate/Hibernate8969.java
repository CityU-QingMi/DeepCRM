	@Test
	public void testSaveOrUpdateTreeWithGeneratedId() {
		clearCounts();

		Session s = openSession();
		Transaction tx = s.beginTransaction();
		NumberedNode root = new NumberedNode( "root" );
		NumberedNode child = new NumberedNode( "child" );
		root.addChild( child );
		s.saveOrUpdate( root );
		tx.commit();
		s.close();

		assertInsertCount( 2 );
		clearCounts();

		root.setDescription( "The root node" );
		child.setDescription( "The child node" );

		NumberedNode secondChild = new NumberedNode( "second child" );

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
		s.createQuery( "delete from NumberedNode where parent is not null" ).executeUpdate();
		s.createQuery( "delete from NumberedNode" ).executeUpdate();
		tx.commit();
		s.close();
	}
