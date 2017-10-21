	private boolean copyState(Object entity, Type[] types, Object[] state, SessionFactory sf) {
		// copy the entity state into the state array and return true if the state has changed
		ClassMetadata metadata = sf.getClassMetadata( entity.getClass() );
		Object[] newState = metadata.getPropertyValues( entity );
		int size = newState.length;
		boolean isDirty = false;
		for ( int index = 0; index < size ; index++ ) {
			if ( ( state[index] == LazyPropertyInitializer.UNFETCHED_PROPERTY &&
					newState[index] != LazyPropertyInitializer.UNFETCHED_PROPERTY ) ||
					( state[index] != newState[index] && !types[index].isEqual( state[index], newState[index] ) ) ) {
				isDirty = true;
				state[index] = newState[index];
			}
		}
		return isDirty;
	}
