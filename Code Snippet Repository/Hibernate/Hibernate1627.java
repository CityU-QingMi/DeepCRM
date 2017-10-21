	@Override
	@SuppressWarnings("")
	public Object readFrom(
			ResultSet rs,
			CollectionPersister persister,
			CollectionAliases descriptor,
			Object owner) throws HibernateException, SQLException {
		final Object element = persister.readElement( rs, owner, descriptor.getSuffixedElementAliases(), getSession() );
		if ( element != null ) {
			final Object index = persister.readIndex( rs, descriptor.getSuffixedIndexAliases(), getSession() );
			if ( loadingEntries == null ) {
				loadingEntries = new ArrayList<>();
			}
			loadingEntries.add( new Object[] { index, element } );
		}
		return element;
	}
