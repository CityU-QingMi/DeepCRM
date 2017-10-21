	@Test
	public void testGetParentMakeReadOnlyThenMergeDetachedParentWithNewChildC() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		VersionedNode parent = new VersionedNode( "parent", "parent" );
		s.persist( parent );
		s.getTransaction().commit();
		s.close();

		clearCounts();

		parent.setName( "new parent name" );
		VersionedNode child = new VersionedNode( "child", "child");
		parent.addChild( child );

		s = openSession();
		s.beginTransaction();
		VersionedNode parentManaged = ( VersionedNode ) s.get( VersionedNode.class, parent.getId() );
		s.setReadOnly( parentManaged, true );
		VersionedNode parentMerged = ( VersionedNode ) s.merge( parent );
		assertSame( parentManaged, parentMerged );
		s.getTransaction().commit();
		s.close();

		assertUpdateCount( 1 );
		assertInsertCount( 1 );
		clearCounts();

		s = openSession();
		s.beginTransaction();
		parent = ( VersionedNode ) s.get( VersionedNode.class, parent.getId() );
		child = ( VersionedNode ) s.get( VersionedNode.class, child.getId() );
		assertEquals( parent.getName(), "parent" );
		assertEquals( 1, parent.getChildren().size() );
		assertEquals( 1, parent.getVersion() );
		assertSame( parent, child.getParent() );
		assertSame( child, parent.getChildren().iterator().next() );
		assertEquals( 0, child.getVersion() );
		s.delete( parent );
		s.delete( child );
		s.getTransaction().commit();
		s.close();

		assertUpdateCount( 0 );
		assertDeleteCount( 2 );
	}
