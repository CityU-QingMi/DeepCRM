	@Override
	public boolean equalsSnapshot(CollectionPersister persister) throws HibernateException {
		final Type elementType = persister.getElementType();
		final java.util.Map sn = (java.util.Map) getSnapshot();
		if ( sn.size()!=set.size() ) {
			return false;
		}
		else {
			for ( Object test : set ) {
				final Object oldValue = sn.get( test );
				if ( oldValue == null || elementType.isDirty( oldValue, test, getSession() ) ) {
					return false;
				}
			}
			return true;
		}
	}
