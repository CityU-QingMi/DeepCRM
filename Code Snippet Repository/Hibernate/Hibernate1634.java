	@Override
	@SuppressWarnings("")
	public Iterator getDeletes(CollectionPersister persister, boolean indexIsFormula) throws HibernateException {
		final Type elementType = persister.getElementType();
		final java.util.Map sn = (java.util.Map) getSnapshot();
		final ArrayList deletes = new ArrayList( sn.size() );

		Iterator itr = sn.keySet().iterator();
		while ( itr.hasNext() ) {
			final Object test = itr.next();
			if ( !set.contains( test ) ) {
				// the element has been removed from the set
				deletes.add( test );
			}
		}

		itr = set.iterator();
		while ( itr.hasNext() ) {
			final Object test = itr.next();
			final Object oldValue = sn.get( test );
			if ( oldValue!=null && elementType.isDirty( test, oldValue, getSession() ) ) {
				// the element has changed
				deletes.add( oldValue );
			}
		}

		return deletes.iterator();
	}
