	@Test
	public void testUpdateSetModifiable() throws Exception {
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
		node.setName( "node-name" );
		s.setReadOnly( node, false );
		s.getTransaction().commit();
		s.close();

		assertUpdateCount( 1 );
		assertInsertCount( 0 );
		clearCounts();

		s = openSession();
		s.beginTransaction();
		node = ( VersionedNode ) s.get( VersionedNode.class, node.getId() );
		assertEquals( "node-name", node.getName() );
		assertEquals( 1, node.getVersion() );
		s.setReadOnly( node, true );
		s.delete( node );
		s.getTransaction().commit();
		s.close();

		assertUpdateCount( 0 );
		assertDeleteCount( 1 );
	}
