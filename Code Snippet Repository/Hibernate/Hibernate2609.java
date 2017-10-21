	private void validateLhs(FromReferenceNode lhs) {
		// make sure the lhs is "assignable"...
		if ( !lhs.isResolved() ) {
			throw new UnsupportedOperationException( "cannot validate assignablity of unresolved node" );
		}

		if ( lhs.getDataType().isCollectionType() ) {
			throw new QueryException( "collections not assignable in update statements" );
		}
		else if ( lhs.getDataType().isComponentType() ) {
			throw new QueryException( "Components currently not assignable in update statements" );
		}
		else if ( lhs.getDataType().isEntityType() ) {
			// currently allowed...
		}

		// TODO : why aren't these the same?
		if ( lhs.getImpliedJoin() != null || lhs.getFromElement().isImplied() ) {
			throw new QueryException( "Implied join paths are not assignable in update statements" );
		}
	}
