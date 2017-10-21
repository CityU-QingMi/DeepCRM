	public void newChildren(Collection children) {
		if ( children == getChildren() ) {
			return;
		}
		if ( getChildren() != null ) {
			for ( Iterator it = getChildren().iterator(); it.hasNext(); ) {
				ChildWithManyToOne child =  ( ChildWithManyToOne ) it.next();
				child.setParent( null );
			}
		}
		if ( children != null ) {
			for ( Iterator it = children.iterator(); it.hasNext(); ) {
				ChildWithManyToOne child = ( ChildWithManyToOne ) it.next();
				child.setParent( this );
			}
		}
		super.newChildren( children );
	}
