	@Test
	public void testGetChildMakeReadOnlyThenMergeDetachedChildWithNewParent() throws Exception {
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
		VersionedNode childManaged = ( VersionedNode ) s.get( VersionedNode.class, child.getId() );
		s.setReadOnly( childManaged, true );
		VersionedNode childMerged = ( VersionedNode ) s.merge( child );
		assertSame( childManaged, childMerged );
		s.getTransaction().commit();
		s.close();

		assertUpdateCount( 1 );
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
		assertEquals( 1, parent.getVersion() ); // / hmmm, why is was version updated?
		s.setReadOnly( parent, true );
		s.setReadOnly( child, true );
		s.delete( parent );
		s.delete( child );
		s.getTransaction().commit();
		s.close();

		assertUpdateCount( 0 );
		assertDeleteCount( 2 );
	}
