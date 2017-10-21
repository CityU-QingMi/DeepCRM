	@Override
	@SuppressWarnings("")
	public Iterator getDeletes(CollectionPersister persister, boolean indexIsFormula) throws HibernateException {
		final Type elementType = persister.getElementType();
		final ArrayList deletes = new ArrayList();
		final List sn = (List) getSnapshot();
		final Iterator olditer = sn.iterator();
		int i=0;
		while ( olditer.hasNext() ) {
			final Object old = olditer.next();
			final Iterator newiter = bag.iterator();
			boolean found = false;
			if ( bag.size()>i && elementType.isSame( old, bag.get( i++ ) ) ) {
			//a shortcut if its location didn't change!
				found = true;
			}
			else {
				//search for it
				//note that this code is incorrect for other than one-to-many
				while ( newiter.hasNext() ) {
					if ( elementType.isSame( old, newiter.next() ) ) {
						found = true;
						break;
					}
				}
			}
			if ( !found ) {
				deletes.add( old );
			}
		}
		return deletes.iterator();
	}
