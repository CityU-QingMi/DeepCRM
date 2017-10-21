	@Override
	public void preInsert(CollectionPersister persister) throws HibernateException {
		final Iterator itr = values.iterator();
		int i = 0;
		while ( itr.hasNext() ) {
			final Object entry = itr.next();
			final Integer loc = i++;
			if ( !identifiers.containsKey( loc ) ) {
				//TODO: native ids
				final Serializable id = persister.getIdentifierGenerator().generate( getSession(), entry );
				identifiers.put( loc, id );
			}
		}
	}
