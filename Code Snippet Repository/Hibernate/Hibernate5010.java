	@Override
	public boolean isModified(Object old, Object current, boolean[] checkable, SharedSessionContractImplementor session)
			throws HibernateException {
		if ( current == null ) {
			return old != null;
		}
		else if ( old == null ) {
			return true;
		}

		final ObjectTypeCacheEntry holder = (ObjectTypeCacheEntry) old;
		final boolean[] idCheckable = new boolean[checkable.length-1];
		System.arraycopy( checkable, 1, idCheckable, 0, idCheckable.length );
		return ( checkable[0] && !holder.entityName.equals( session.bestGuessEntityName( current ) ) )
				|| identifierType.isModified( holder.id, getIdentifier( current, session ), idCheckable, session );
	}
