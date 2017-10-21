	@Test
	@FailureExpected( jiraKey = "" )
	public void testSetReadOnlyUpdateSetModifiable() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		VersionedNode node = new VersionedNode( "node", "node" );
		s.persist( node );
		s.getTransaction().commit();
		s.close();

		clearCounts();

		s = openSession();

		s.beginTransaction();
		node = ( VersionedNode ) s.get( VersionedNode.class, node.getId() );
		s.setReadOnly( node, true );
		node.setName( "node-name" );
		s.setReadOnly( node, false );
		s.getTransaction().commit();
		s.close();

		assertUpdateCount( 1 );
		assertInsertCount( 0 );

		s = openSession();
		s.beginTransaction();
		node = ( VersionedNode ) s.get( VersionedNode.class, node.getId() );
		assertEquals( "node-name", node.getName() );
		assertEquals( 1, node.getVersion() );
		s.delete( node );
		s.getTransaction().commit();
		s.close();
	}
