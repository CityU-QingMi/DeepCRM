	@Override
	@SuppressWarnings("")
	public Object readFrom(ResultSet rs, CollectionPersister persister, CollectionAliases descriptor, Object owner)
			throws HibernateException, SQLException {
		final Object element = persister.readElement( rs, owner, descriptor.getSuffixedElementAliases(), getSession() ) ;
		final int index = (Integer) persister.readIndex( rs, descriptor.getSuffixedIndexAliases(), getSession() );

		//pad with nulls from the current last element up to the new index
		for ( int i = list.size(); i<=index; i++) {
			list.add( i, null );
		}

		list.set( index, element );
		return element;
	}
