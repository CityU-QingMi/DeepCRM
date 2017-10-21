	@SuppressWarnings({"", ""})
	protected Serializable snapshot(BasicCollectionPersister persister, EntityMode entityMode)
			throws HibernateException {
		final TreeMap clonedSet = new TreeMap( comparator );
		for ( Object setElement : set ) {
			final Object copy = persister.getElementType().deepCopy( setElement, persister.getFactory() );
			clonedSet.put( copy, copy );
		}
		return clonedSet;
	}
