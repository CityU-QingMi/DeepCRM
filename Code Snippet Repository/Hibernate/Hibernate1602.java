	@Override
	public boolean needsUpdating(Object entry, int i, Type elemType) throws HibernateException {
		if ( entry == null ) {
			return false;
		}

		final Map snap = (Map) getSnapshot();
		final Object id = identifiers.get( i );
		if ( id == null ) {
			return false;
		}

		final Object old = snap.get( id );
		return old != null && elemType.isDirty( old, entry, getSession() );
	}
