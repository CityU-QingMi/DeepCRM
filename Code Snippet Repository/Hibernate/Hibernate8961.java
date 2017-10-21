	@Test
    @SuppressWarnings( {""})
	public void testNoExtraUpdatesOnMerge() throws Exception {
		Session s = openSession();
        s.beginTransaction();
		Node node = new Node( "test" );
		s.persist( node );
		s.getTransaction().commit();
		s.close();

		clearCounts();

		// node is now detached, but we have made no changes.  so attempt to merge it
		// into this new session; this should cause no updates...
		s = openSession();
		s.beginTransaction();
		node = ( Node ) s.merge( node );
		s.getTransaction().commit();
		s.close();

		assertUpdateCount( 0 );
		assertInsertCount( 0 );

		///////////////////////////////////////////////////////////////////////
		// as a control measure, now update the node while it is detached and
		// make sure we get an update as a result...
		node.setDescription( "new description" );
		s = openSession();
		s.beginTransaction();
		node = ( Node ) s.merge( node );
		s.getTransaction().commit();
		s.close();
		assertUpdateCount( 1 );
		assertInsertCount( 0 );
		///////////////////////////////////////////////////////////////////////

		cleanup();
    }
