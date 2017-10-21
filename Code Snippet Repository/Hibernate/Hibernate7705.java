	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testMoveCollectionToDifferentParent() {
		CollectionListeners listeners = new CollectionListeners( sessionFactory() );
		ParentWithCollection parent = createParentWithOneChild( "parent", "child" );
		ParentWithCollection otherParent = createParentWithOneChild( "otherParent", "otherChild" );
		listeners.clear();
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		parent = ( ParentWithCollection ) s.get( parent.getClass(), parent.getId() );
		otherParent = ( ParentWithCollection ) s.get( otherParent.getClass(), otherParent.getId() );
		Collection otherCollectionOrig = otherParent.getChildren();
		otherParent.newChildren( parent.getChildren() );
		parent.newChildren( null );
		tx.commit();
		s.close();
		int index = 0;
		Child otherChildOrig = null;
		if ( ( ( PersistentCollection ) otherCollectionOrig ).wasInitialized() ) {
			checkResult( listeners, listeners.getInitializeCollectionListener(), otherParent, otherCollectionOrig, index++ );
			otherChildOrig = ( Child ) otherCollectionOrig.iterator().next();
			if ( otherChildOrig instanceof ChildWithBidirectionalManyToMany ) {
				checkResult( listeners, listeners.getInitializeCollectionListener(), ( ChildWithBidirectionalManyToMany ) otherChildOrig, index++ );
			}
		}
		checkResult( listeners, listeners.getInitializeCollectionListener(), parent, otherParent.getChildren(), index++ );
		Child otherChild = ( Child ) otherParent.getChildren().iterator().next();
		if ( otherChild instanceof ChildWithBidirectionalManyToMany ) {
			checkResult( listeners, listeners.getInitializeCollectionListener(), ( ChildWithBidirectionalManyToMany ) otherChild, index++ );
		}
		checkResult( listeners, listeners.getPreCollectionRemoveListener(), parent, otherParent.getChildren(), index++ );
		checkResult( listeners, listeners.getPostCollectionRemoveListener(), parent, otherParent.getChildren(), index++ );
		checkResult( listeners, listeners.getPreCollectionRemoveListener(), otherParent, otherCollectionOrig, index++ );
		checkResult( listeners, listeners.getPostCollectionRemoveListener(), otherParent, otherCollectionOrig, index++ );
		if ( otherChild instanceof ChildWithBidirectionalManyToMany ) {
			checkResult( listeners, listeners.getPreCollectionUpdateListener(), ( ChildWithBidirectionalManyToMany ) otherChildOrig, index++ );
			checkResult( listeners, listeners.getPostCollectionUpdateListener(), ( ChildWithBidirectionalManyToMany ) otherChildOrig, index++ );
			checkResult( listeners, listeners.getPreCollectionUpdateListener(), ( ChildWithBidirectionalManyToMany ) otherChild, index++ );
			checkResult( listeners, listeners.getPostCollectionUpdateListener(), ( ChildWithBidirectionalManyToMany ) otherChild, index++ );
		}
		checkResult( listeners, listeners.getPreCollectionRecreateListener(), otherParent, index++ );
		checkResult( listeners, listeners.getPostCollectionRecreateListener(), otherParent, index++ );
		// there should also be pre- and post-recreate collection events for parent, but thats broken now;
		// this is covered in BrokenCollectionEventTest
		checkNumberOfResults( listeners, index );
	}
