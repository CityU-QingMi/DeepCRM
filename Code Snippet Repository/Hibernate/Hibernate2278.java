	public void executeActions() throws HibernateException {
		if ( hasUnresolvedEntityInsertActions() ) {
			throw new IllegalStateException( "About to execute actions, but there are unresolved entity insert actions." );
		}

		for ( ListProvider listProvider : EXECUTABLE_LISTS_MAP.values() ) {
			ExecutableList<?> l = listProvider.get( this );
			if ( l != null && !l.isEmpty() ) {
				executeActions( l );
			}
		}
	}
