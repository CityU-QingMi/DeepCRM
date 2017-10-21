	@Override
	public int compareTo(Object other) {
		final CollectionAction action = (CollectionAction) other;

		// sort first by role name
		final int roleComparison = collectionRole.compareTo( action.collectionRole );
		if ( roleComparison != 0 ) {
			return roleComparison;
		}
		else {
			//then by fk
			return persister.getKeyType().compare( key, action.key );
		}
	}
