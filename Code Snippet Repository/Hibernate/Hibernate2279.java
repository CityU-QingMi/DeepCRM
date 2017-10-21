	public boolean areTablesToBeUpdated(@SuppressWarnings("rawtypes") Set tables) {
		if ( tables.isEmpty() ) {
			return false;
		}
		for ( ListProvider listProvider : EXECUTABLE_LISTS_MAP.values() ) {
			ExecutableList<?> l = listProvider.get( this );
			if ( areTablesToBeUpdated( l, tables ) ) {
				return true;
			}
		}
		if(unresolvedInsertions == null) {
			return false;
		}
		return areTablesToBeUpdated( unresolvedInsertions, tables );
	}
