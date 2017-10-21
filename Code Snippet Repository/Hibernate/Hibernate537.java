	@Override
	public int compareTo(Object other) {
		final EntityAction action = (EntityAction) other;
		//sort first by entity name
		final int roleComparison = entityName.compareTo( action.entityName );
		if ( roleComparison != 0 ) {
			return roleComparison;
		}
		else {
			//then by id
			return persister.getIdentifierType().compare( id, action.id );
		}
	}
