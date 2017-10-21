	@Override
	@SuppressWarnings("")
	public void initializeFromCache(CollectionPersister persister, Serializable disassembled, Object owner)
			throws HibernateException {
		final Serializable[] array = (Serializable[]) disassembled;
		final int size = array.length;
		beforeInitialize( persister, size );
		for ( Serializable item : array ) {
			final Object element = persister.getElementType().assemble( item, getSession(), owner );
			if ( element != null ) {
				bag.add( element );
			}
		}
	}
