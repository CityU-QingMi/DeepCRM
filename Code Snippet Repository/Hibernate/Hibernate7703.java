	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testMoveAllChildrenToDifferentParent() {
		CollectionListeners listeners = new CollectionListeners( sessionFactory() );
		ParentWithCollection parent = createParentWithOneChild( "parent", "child" );
		ParentWithCollection otherParent = createParentWithOneChild( "otherParent", "otherChild" );
		Child child = ( Child ) parent.getChildren().iterator().next();
		listeners.clear();
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		parent = ( ParentWithCollection ) s.get( parent.getClass(), parent.getId() );
		otherParent = ( ParentWithCollection ) s.get( otherParent.getClass(), otherParent.getId() );
		if ( child instanceof Entity ) {
			child = ( Child ) s.get( child.getClass(), ( ( Entity ) child ).getId() );
		}
		otherParent.addAllChildren( parent.getChildren() );
		parent.clearChildren();
		tx.commit();
		s.close();
		int index = 0;
		if ( ( ( PersistentCollection ) parent.getChildren() ).wasInitialized() ) {
			checkResult( listeners, listeners.getInitializeCollectionListener(), parent, index++ );
		}
		if ( ( ( PersistentCollection ) otherParent.getChildren() ).wasInitialized() ) {
			checkResult( listeners, listeners.getInitializeCollectionListener(), otherParent, index++ );
		}
		if ( child instanceof ChildWithBidirectionalManyToMany ) {
			checkResult( listeners, listeners.getInitializeCollectionListener(), ( ChildWithBidirectionalManyToMany ) child, index++ );
		}
		checkResult( listeners, listeners.getPreCollectionUpdateListener(), parent, index++ );
		checkResult( listeners, listeners.getPostCollectionUpdateListener(), parent, index++ );
		checkResult( listeners, listeners.getPreCollectionUpdateListener(), otherParent, index++ );
		checkResult( listeners, listeners.getPostCollectionUpdateListener(), otherParent, index++ );
		if ( child instanceof ChildWithBidirectionalManyToMany ) {
			checkResult( listeners, listeners.getPreCollectionUpdateListener(), ( ChildWithBidirectionalManyToMany ) child, index++ );
			checkResult( listeners, listeners.getPostCollectionUpdateListener(), ( ChildWithBidirectionalManyToMany ) child, index++ );
		}
		checkNumberOfResults( listeners, index );
	}
