	@Override
	@SuppressWarnings( {""})
	public Serializable getSnapshot(CollectionPersister persister) throws HibernateException {
		final HashMap clonedMap = new HashMap( map.size() );
		for ( Object o : map.entrySet() ) {
			final Entry e = (Entry) o;
			final Object copy = persister.getElementType().deepCopy( e.getValue(), persister.getFactory() );
			clonedMap.put( e.getKey(), copy );
		}
		return clonedMap;
	}
