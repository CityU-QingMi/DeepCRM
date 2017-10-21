	@Override
	@SuppressWarnings("")
	public Serializable disassemble(CollectionPersister persister) throws HibernateException {
		final Serializable[] result = new Serializable[ set.size() ];
		final Iterator itr = set.iterator();
		int i=0;
		while ( itr.hasNext() ) {
			result[i++] = persister.getElementType().disassemble( itr.next(), getSession(), null );
		}
		return result;
	}
