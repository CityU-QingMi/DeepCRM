	@Override
	@SuppressWarnings("")
	public void initializeFromCache(CollectionPersister persister, Serializable disassembled, Object owner)
			throws HibernateException {
		final Serializable[] array = (Serializable[]) disassembled;
		final int size = array.length;
		beforeInitialize( persister, size );
		for ( int i = 0; i < size; i+=2 ) {
			map.put(
					persister.getIndexType().assemble( array[i], getSession(), owner ),
					persister.getElementType().assemble( array[i+1], getSession(), owner )
			);
		}
	}
