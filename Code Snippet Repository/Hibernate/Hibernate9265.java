	@Test
	public void testAddNewParentToReadOnlyChild() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		VersionedNode child = new VersionedNode( "child", "child" );
		s.persist( child );
		s.getTransaction().commit();
		s.close();

		clearCounts();

		s = openSession();
		s.beginTransaction();
		VersionedNode childManaged = ( VersionedNode ) s.get( VersionedNode.class, child.getId() );
		s.setReadOnly( childManaged, true );
		childManaged.setName( "new child name" );
		VersionedNode parent = new VersionedNode( "parent", "parent");
		parent.addChild( childManaged );
		s.getTransaction().commit();
		s.close();

		assertUpdateCount( 0 );
		assertInsertCount( 1 );

		s = openSession();
		s.beginTransaction();
		child = ( VersionedNode ) s.get( VersionedNode.class, child.getId() );
		assertEquals( "child", child.getName() );
		assertNull( child.getParent() );
		assertEquals( 0, child.getVersion() );
		parent = ( VersionedNode ) s.get( VersionedNode.class, parent.getId() );
		assertNotNull( parent );
		s.setReadOnly( child, true );
		s.delete( child );
		s.getTransaction().commit();
		s.close();

		assertUpdateCount( 0 );
		assertDeleteCount( 1 );
	}
