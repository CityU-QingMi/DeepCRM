	@Override
	public Object readFrom(
			ResultSet rs,
			CollectionPersister persister,
			CollectionAliases descriptor,
			Object owner) throws HibernateException, SQLException {
		final Object element = persister.readElement( rs, owner, descriptor.getSuffixedElementAliases(), getSession() );
		final Object old = identifiers.put(
			values.size(),
			persister.readIdentifier( rs, descriptor.getSuffixedIdentifierAlias(), getSession() )
		);

		if ( old == null ) {
			//maintain correct duplication if loaded in a cartesian product
			values.add( element );
		}
		return element;
	}
