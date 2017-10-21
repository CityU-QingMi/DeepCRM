	public void clear() {
		for ( ListProvider listProvider : EXECUTABLE_LISTS_MAP.values() ) {
			ExecutableList<?> l = listProvider.get( this );
			if( l != null ) {
				l.clear();
			}
		}
		if( unresolvedInsertions != null ) {
			unresolvedInsertions.clear();
		}
	}
