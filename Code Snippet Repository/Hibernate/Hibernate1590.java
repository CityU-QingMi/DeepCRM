	@Override
	@SuppressWarnings("")
	public Serializable getSnapshot(CollectionPersister persister) throws HibernateException {
		final HashMap map = new HashMap( values.size() );
		final Iterator iter = values.iterator();
		int i=0;
		while ( iter.hasNext() ) {
			final Object value = iter.next();
			map.put(
					identifiers.get( i++ ),
					persister.getElementType().deepCopy( value, persister.getFactory() )
			);
		}
		return map;
	}
