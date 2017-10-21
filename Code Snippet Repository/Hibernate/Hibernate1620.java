	@Override
	@SuppressWarnings("")
	public Iterator getDeletes(CollectionPersister persister, boolean indexIsFormula) throws HibernateException {
		final List deletes = new ArrayList();
		for ( Object o : ((Map) getSnapshot()).entrySet() ) {
			final Entry e = (Entry) o;
			final Object key = e.getKey();
			if ( e.getValue() != null && map.get( key ) == null ) {
				deletes.add( indexIsFormula ? e.getValue() : key );
			}
		}
		return deletes.iterator();
	}
