	@Override
	public boolean equalsSnapshot(CollectionPersister persister) throws HibernateException {
		final Type elementType = persister.getElementType();
		final Map snapshotMap = (Map) getSnapshot();
		if ( snapshotMap.size() != this.map.size() ) {
			return false;
		}

		for ( Object o : map.entrySet() ) {
			final Entry entry = (Entry) o;
			if ( elementType.isDirty( entry.getValue(), snapshotMap.get( entry.getKey() ), getSession() ) ) {
				return false;
			}
		}
		return true;
	}
