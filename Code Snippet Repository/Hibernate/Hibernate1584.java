	@Override
	public Serializable disassemble(CollectionPersister persister)
			throws HibernateException {
		final int length = bag.size();
		final Serializable[] result = new Serializable[length];
		for ( int i=0; i<length; i++ ) {
			result[i] = persister.getElementType().disassemble( bag.get( i ), getSession(), null );
		}
		return result;
	}
