	@Test
	public void testAddNewChildToReadOnlyParent() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		VersionedNode parent = new VersionedNode( "parent", "parent" );
		s.persist( parent );
		s.getTransaction().commit();
		s.close();

		clearCounts();

		s = openSession();
		s.beginTransaction();
		VersionedNode parentManaged = ( VersionedNode ) s.get( VersionedNode.class, parent.getId() );
		s.setReadOnly( parentManaged, true );
		parentManaged.setName( "new parent name" );
		VersionedNode child = new VersionedNode( "child", "child");
		parentManaged.addChild( child );
		s.getTransaction().commit();
		s.close();

		assertUpdateCount( 1 );
		assertInsertCount( 1 );

		s = openSession();
		s.beginTransaction();
		parent = ( VersionedNode ) s.get( VersionedNode.class, parent.getId() );
		assertEquals( "parent", parent.getName() );
		assertEquals( 1, parent.getChildren().size() );
		assertEquals( 1, parent.getVersion() );
		child = ( VersionedNode ) s.get( VersionedNode.class, child.getId() );
		assertNotNull( child );
		s.delete( parent );
		s.getTransaction().commit();
		s.close();
	}
