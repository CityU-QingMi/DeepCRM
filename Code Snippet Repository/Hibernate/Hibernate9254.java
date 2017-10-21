	@Test
	public void testUpdateChildWithNewParentCommitWithReadOnlyChild() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		VersionedNode child = new VersionedNode( "child", "child" );
		s.persist( child );
		s.getTransaction().commit();
		s.close();

		clearCounts();

		child.setName( "new child name" );
		VersionedNode parent = new VersionedNode( "parent", "parent");
		parent.addChild( child );

		s = openSession();
		s.beginTransaction();
		s.update( child );
		s.setReadOnly( child, true );
		s.getTransaction().commit();
		s.close();

		assertUpdateCount( 0 );
		assertInsertCount( 1 );
		clearCounts();

		s = openSession();
		s.beginTransaction();
		parent = ( VersionedNode ) s.get( VersionedNode.class, parent.getId() );
		child = ( VersionedNode ) s.get( VersionedNode.class, child.getId() );
		assertEquals( child.getName(), "child" );
		assertNull( child.getParent() );
		assertEquals( 0, child.getVersion() );
		assertNotNull( parent );
		assertEquals( 0, parent.getChildren().size() );
		assertEquals( 0, parent.getVersion() );
		s.setReadOnly( parent, true );
		s.setReadOnly( child, true );
		s.delete( parent );
		s.delete( child );
		s.getTransaction().commit();
		s.close();

		assertUpdateCount( 0 );
		assertDeleteCount( 2 );
	}
