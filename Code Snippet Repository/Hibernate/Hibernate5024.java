	@Override
	public String toLoggableString(Object value, SessionFactoryImplementor factory)
			throws HibernateException {
		if ( value == null ) {
			return "null";
		}

		if ( !getReturnedClass().isInstance( value ) && !PersistentCollection.class.isInstance( value ) ) {
			// its most likely the collection-key
			final CollectionPersister persister = getPersister( factory );
			if ( persister.getKeyType().getReturnedClass().isInstance( value ) ) {
				return getRole() + "#" + getPersister( factory ).getKeyType().toLoggableString( value, factory );
			}
			else {
				// although it could also be the collection-id
				if ( persister.getIdentifierType() != null
						&& persister.getIdentifierType().getReturnedClass().isInstance( value ) ) {
					return getRole() + "#" + getPersister( factory ).getIdentifierType().toLoggableString( value, factory );
				}
			}
		}
		return renderLoggableString( value, factory );
	}
