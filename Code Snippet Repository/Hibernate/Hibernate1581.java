	@Override
	public boolean equalsSnapshot(CollectionPersister persister) throws HibernateException {
		final Type elementType = persister.getElementType();
		final List sn = (List) getSnapshot();
		if ( sn.size() != bag.size() ) {
			return false;
		}
		for ( Object elt : bag ) {
			final boolean unequal = countOccurrences( elt, bag, elementType ) != countOccurrences( elt, sn, elementType );
			if ( unequal ) {
				return false;
			}
		}
		return true;
	}
