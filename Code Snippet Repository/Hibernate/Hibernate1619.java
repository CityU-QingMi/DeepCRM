	@Override
	@SuppressWarnings("")
	public Serializable disassemble(CollectionPersister persister) throws HibernateException {
		final Serializable[] result = new Serializable[ map.size() * 2 ];
		final Iterator itr = map.entrySet().iterator();
		int i=0;
		while ( itr.hasNext() ) {
			final Map.Entry e = (Map.Entry) itr.next();
			result[i++] = persister.getIndexType().disassemble( e.getKey(), getSession(), null );
			result[i++] = persister.getElementType().disassemble( e.getValue(), getSession(), null );
		}
		return result;

	}
