	public void setOrigin(FromElement origin, boolean manyToMany) {
		this.origin = origin;
		this.manyToMany = manyToMany;
		origin.addDestination( this );
		if ( origin.getFromClause() == this.getFromClause() ) {
			// TODO: Figure out a better way to get the FROM elements in a proper tree structure.
			// If this is not the destination of a many-to-many, add it as a child of the origin.
			if ( manyToMany ) {
				ASTUtil.appendSibling( origin, this );
			}
			else {
				if ( !getWalker().isInFrom() && !getWalker().isInSelect() && !getWalker().isInEntityGraph() ) {
					getFromClause().addChild( this );
				}
				else {
					origin.addChild( this );
				}
			}
		}
		else if ( !getWalker().isInFrom() ) {
			// HHH-276 : implied joins in a subselect where clause - The destination needs to be added
			// to the destination's from clause.
			getFromClause().addChild( this );	// Not sure if this is will fix everything, but it works.
		}
		else {
			// Otherwise, the destination node was implied by the FROM clause and the FROM clause processor
			// will automatically add it in the right place.
		}
	}
