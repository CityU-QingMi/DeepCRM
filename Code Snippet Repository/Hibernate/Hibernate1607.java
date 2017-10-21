	@Override
	@SuppressWarnings("")
	public void initializeFromCache(CollectionPersister persister, Serializable disassembled, Object owner)
			throws HibernateException {
		final Serializable[] array = (Serializable[]) disassembled;
		final int size = array.length;
		beforeInitialize( persister, size );
		for ( Serializable arrayElement : array ) {
			list.add( persister.getElementType().assemble( arrayElement, getSession(), owner ) );
		}
	}
