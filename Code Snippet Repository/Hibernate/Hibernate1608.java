	@Override
	@SuppressWarnings("")
	public Serializable disassemble(CollectionPersister persister) throws HibernateException {
		final int length = list.size();
		final Serializable[] result = new Serializable[length];
		for ( int i=0; i<length; i++ ) {
			result[i] = persister.getElementType().disassemble( list.get( i ), getSession(), null );
		}
		return result;
	}
