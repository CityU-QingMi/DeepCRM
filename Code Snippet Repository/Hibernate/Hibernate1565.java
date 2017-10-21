	@Override
	public boolean equalsSnapshot(CollectionPersister persister) throws HibernateException {
		final Type elementType = persister.getElementType();
		final Serializable snapshot = getSnapshot();
		final int xlen = Array.getLength( snapshot );
		if ( xlen!= Array.getLength( array ) ) {
			return false;
		}
		for ( int i=0; i<xlen; i++) {
			if ( elementType.isDirty( Array.get( snapshot, i ), Array.get( array, i ), getSession() ) ) {
				return false;
			}
		}
		return true;
	}
