	@Override
	@SuppressWarnings( {""})
	public Serializable getSnapshot(CollectionPersister persister) throws HibernateException {
		final HashMap clonedSet = new HashMap( set.size() );
		for ( Object aSet : set ) {
			final Object copied = persister.getElementType().deepCopy( aSet, persister.getFactory() );
			clonedSet.put( copied, copied );
		}
		return clonedSet;
	}
