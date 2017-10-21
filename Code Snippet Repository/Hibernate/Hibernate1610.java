	@Override
	public boolean equalsSnapshot(CollectionPersister persister) throws HibernateException {
		final Type elementType = persister.getElementType();
		final List sn = (List) getSnapshot();
		if ( sn.size()!=this.list.size() ) {
			return false;
		}
		final Iterator itr = list.iterator();
		final Iterator snapshotItr = sn.iterator();
		while ( itr.hasNext() ) {
			if ( elementType.isDirty( itr.next(), snapshotItr.next(), getSession() ) ) {
				return false;
			}
		}
		return true;
	}
