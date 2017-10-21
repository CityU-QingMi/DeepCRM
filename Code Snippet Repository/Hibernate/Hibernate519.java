	protected final Serializable getKey() {
		Serializable finalKey = key;
		if ( key instanceof DelayedPostInsertIdentifier ) {
			// need to look it up from the persistence-context
			finalKey = session.getPersistenceContext().getEntry( collection.getOwner() ).getId();
			if ( finalKey == key ) {
				// we may be screwed here since the collection action is about to execute
				// and we do not know the final owner key value
			}
		}
		return finalKey;
	}
