	@Override
	public Serializable disassemble(CollectionPersister persister) throws HibernateException {
		final int length = Array.getLength( array );
		final Serializable[] result = new Serializable[length];
		for ( int i=0; i<length; i++ ) {
			result[i] = persister.getElementType().disassemble( Array.get( array,i ), getSession(), null );
		}

		return result;
	}
