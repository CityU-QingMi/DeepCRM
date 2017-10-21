	@Override
	public boolean equalsSnapshot(CollectionPersister persister) throws HibernateException {
		final Type elementType = persister.getElementType();
		final Map snap = (Map) getSnapshot();
		if ( snap.size()!= values.size() ) {
			return false;
		}
		for ( int i=0; i<values.size(); i++ ) {
			final Object value = values.get( i );
			final Object id = identifiers.get( i );
			if ( id == null ) {
				return false;
			}
			final Object old = snap.get( id );
			if ( elementType.isDirty( old, value, getSession() ) ) {
				return false;
			}
		}
		return true;
	}
