	@Override
	public Serializable disassemble(CollectionPersister persister)
			throws HibernateException {
		final Serializable[] result = new Serializable[ values.size() * 2 ];
		int i = 0;
		for ( int j=0; j< values.size(); j++ ) {
			final Object value = values.get( j );
			result[i++] = persister.getIdentifierType().disassemble( identifiers.get( j ), getSession(), null );
			result[i++] = persister.getElementType().disassemble( value, getSession(), null );
		}
		return result;
	}
