	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testUpdateParentTwoChildrenToOne() {
		CollectionListeners listeners = new CollectionListeners( sessionFactory() );
		ParentWithCollection parent = createParentWithOneChild( "parent", "child" );
		assertEquals( 1, parent.getChildren().size() );
		Child oldChild = ( Child ) parent.getChildren().iterator().next();
		listeners.clear();
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		parent = ( ParentWithCollection ) s.get( parent.getClass(), parent.getId() );
		parent.addChild( "new" );
		tx.commit();
		s.close();
		listeners.clear();
		s = openSession();
		tx = s.beginTransaction();
		parent = ( ParentWithCollection ) s.get( parent.getClass(), parent.getId() );
		if ( oldChild instanceof Entity ) {
			oldChild = ( Child ) s.get( oldChild.getClass(), ( ( Entity ) oldChild ).getId() );
		}
		parent.removeChild( oldChild );
		tx.commit();
		s.close();
		int index = 0;
		if ( ( ( PersistentCollection ) parent.getChildren() ).wasInitialized() ) {
			checkResult( listeners, listeners.getInitializeCollectionListener(), parent, index++ );
		}
		if ( oldChild instanceof ChildWithBidirectionalManyToMany ) {
			ChildWithBidirectionalManyToMany oldChildWithManyToMany = ( ChildWithBidirectionalManyToMany ) oldChild;
			if ( ( ( PersistentCollection ) oldChildWithManyToMany.getParents() ).wasInitialized() ) {
				checkResult( listeners, listeners.getInitializeCollectionListener(), oldChildWithManyToMany, index++ );
			}
		}
		checkResult( listeners, listeners.getPreCollectionUpdateListener(), parent, index++ );
		checkResult( listeners, listeners.getPostCollectionUpdateListener(), parent, index++ );
		if ( oldChild instanceof ChildWithBidirectionalManyToMany ) {
			checkResult( listeners, listeners.getPreCollectionUpdateListener(), ( ChildWithBidirectionalManyToMany ) oldChild, index++ );
			checkResult( listeners, listeners.getPostCollectionUpdateListener(), ( ChildWithBidirectionalManyToMany ) oldChild, index++ );
		}
		checkNumberOfResults( listeners, index );
	}
