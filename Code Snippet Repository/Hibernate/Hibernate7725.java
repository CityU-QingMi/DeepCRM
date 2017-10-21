	public void newChildren(Collection children) {
		if ( children == getChildren() ) {
			return;
		}
		if ( getChildren() != null ) {
			for ( Iterator it = getChildren().iterator(); it.hasNext(); ) {
				ChildWithBidirectionalManyToMany child = ( ChildWithBidirectionalManyToMany ) it.next();
				child.removeParent( this );
			}
		}
		if ( children != null ) {
			for ( Iterator it = children.iterator(); it.hasNext(); ) {
				ChildWithBidirectionalManyToMany child = ( ChildWithBidirectionalManyToMany ) it.next();
				child.addParent( this );
			}
		}
		super.newChildren( children );
	}
