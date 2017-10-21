	@Test
	@SuppressWarnings( {""})
	public void testDeleteVersionedWithCollectionNoUpdate() {
		// test adapted from HHH-1564...
		Session s = openSession();
		s.beginTransaction();
		VersionedEntity c = new VersionedEntity( "c1", "child-1" );
		VersionedEntity p = new VersionedEntity( "root", "root");
		p.getChildren().add( c );
		c.setParent( p );
		s.save( p );
		s.getTransaction().commit();
		s.close();

		clearCounts();

		s = openSession();
		s.beginTransaction();
        VersionedEntity loadedParent = ( VersionedEntity ) s.get( VersionedEntity.class, "root" );
        s.delete( loadedParent );
		s.getTransaction().commit();
        s.close();

		assertInsertCount( 0 );
		assertUpdateCount( 0 );
		assertDeleteCount( 2 );
	}
