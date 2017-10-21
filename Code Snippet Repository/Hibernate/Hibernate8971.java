	@Test
	public void testSavePersistentEntityWithUpdate() {
		clearCounts();

		Session s = openSession();
		Transaction tx = s.beginTransaction();
		NumberedNode root = new NumberedNode( "root" );
		root.setName( "a name" );
		s.saveOrUpdate( root );
		tx.commit();
		s.close();

		assertInsertCount( 1 );
		assertUpdateCount( 0 );
		clearCounts();

		s = openSession();
		tx = s.beginTransaction();
		root = ( NumberedNode ) s.get( NumberedNode.class, root.getId() );
		assertEquals( "a name", root.getName() );
		root.setName( "a new name" );
		s.save( root );
		tx.commit();
		s.close();

		assertInsertCount( 0 );
		assertUpdateCount( 1 );
		clearCounts();

		s = openSession();
		tx = s.beginTransaction();
		root = ( NumberedNode ) s.get( NumberedNode.class, root.getId() );
		assertEquals( "a new name", root.getName() );
		s.delete( root );
		tx.commit();
		s.close();
	}
