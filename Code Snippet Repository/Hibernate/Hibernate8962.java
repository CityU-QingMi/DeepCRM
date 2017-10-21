	@Test
	@SuppressWarnings( {"", ""})
	public void testNoExtraUpdatesOnMergeWithCollection() throws Exception {
		Session s = openSession();
        s.beginTransaction();
		Node parent = new Node( "parent" );
		Node child = new Node( "child" );
		parent.getChildren().add( child );
		child.setParent( parent );
		s.persist( parent );
		s.getTransaction().commit();
		s.close();

		clearCounts();

		// parent is now detached, but we have made no changes.  so attempt to merge it
		// into this new session; this should cause no updates...
		s = openSession();
		s.beginTransaction();
		parent = ( Node ) s.merge( parent );
		s.getTransaction().commit();
		s.close();

		assertUpdateCount( 0 );
		assertInsertCount( 0 );

		///////////////////////////////////////////////////////////////////////
		// as a control measure, now update the node while it is detached and
		// make sure we get an update as a result...
		( ( Node ) parent.getChildren().iterator().next() ).setDescription( "child's new description" );
		parent.addChild( new Node( "second child" ) );
		s = openSession();
		s.beginTransaction();
		parent = ( Node ) s.merge( parent );
		s.getTransaction().commit();
		s.close();
		assertUpdateCount( 1 );
		assertInsertCount( 1 );
		///////////////////////////////////////////////////////////////////////

		cleanup();
	}
