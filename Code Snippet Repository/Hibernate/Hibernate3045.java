	public void remove() {
		if ( !single ) {
			throw new UnsupportedOperationException( "Not a single column hibernate query result set" );
		}
		if ( currentResult == null ) {
			throw new IllegalStateException( "Called Iterator.remove() before next()" );
		}
		if ( !( types[0] instanceof EntityType ) ) {
			throw new UnsupportedOperationException( "Not an entity" );
		}

		session.delete(
				( (EntityType) types[0] ).getAssociatedEntityName(),
				currentResult,
				false,
				null
		);
	}
