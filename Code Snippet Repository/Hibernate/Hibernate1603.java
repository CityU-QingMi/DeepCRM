	@Override
	@SuppressWarnings( {""})
	public Serializable getSnapshot(CollectionPersister persister) throws HibernateException {
		final ArrayList clonedList = new ArrayList( list.size() );
		for ( Object element : list ) {
			final Object deepCopy = persister.getElementType().deepCopy( element, persister.getFactory() );
			clonedList.add( deepCopy );
		}
		return clonedList;
	}
