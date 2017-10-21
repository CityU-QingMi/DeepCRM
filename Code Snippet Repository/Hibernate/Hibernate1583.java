	@Override
	@SuppressWarnings("")
	public Serializable getSnapshot(CollectionPersister persister)
			throws HibernateException {
		final ArrayList clonedList = new ArrayList( bag.size() );
		for ( Object item : bag ) {
			clonedList.add( persister.getElementType().deepCopy( item, persister.getFactory() ) );
		}
		return clonedList;
	}
