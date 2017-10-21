	@Override
	@SuppressWarnings("")
	public Iterator getDeletes(CollectionPersister persister, boolean indexIsFormula) throws HibernateException {
		final Map snap = (Map) getSnapshot();
		final List deletes = new ArrayList( snap.keySet() );
		for ( int i=0; i<values.size(); i++ ) {
			if ( values.get( i ) != null ) {
				deletes.remove( identifiers.get( i ) );
			}
		}
		return deletes.iterator();
	}
