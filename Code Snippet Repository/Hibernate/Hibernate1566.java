	@Override
	@SuppressWarnings("")
	public Object readFrom(ResultSet rs, CollectionPersister persister, CollectionAliases descriptor, Object owner)
	throws HibernateException, SQLException {
		final Object element = persister.readElement( rs, owner, descriptor.getSuffixedElementAliases(), getSession() );
		final int index = (Integer) persister.readIndex( rs, descriptor.getSuffixedIndexAliases(), getSession() );
		for ( int i = tempList.size(); i<=index; i++) {
			tempList.add( i, null );
		}
		tempList.set( index, element );
		return element;
	}
