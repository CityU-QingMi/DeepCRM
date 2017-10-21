	@Test
	@SuppressWarnings( {""})
	public void testNoUpdatesOnCreateVersionedWithCollection() {
		clearCounts();

		Session s = openSession();
		Transaction tx = s.beginTransaction();
		VersionedEntity root = new VersionedEntity( "root", "root" );
		VersionedEntity child = new VersionedEntity( "c1", "child-1" );
		root.getChildren().add( child );
		child.setParent( root );
		s.save(root);
		tx.commit();
		s.close();

		assertInsertCount( 2 );
		assertUpdateCount( 0 );
		assertDeleteCount( 0 );

		s = openSession();
		tx = s.beginTransaction();
		s.delete( root );
		tx.commit();
		s.close();

		assertUpdateCount( 0 );
		assertDeleteCount( 2 );
	}
