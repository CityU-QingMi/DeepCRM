	private static void addIdToCollectionInfoString(
			CollectionPersister persister,
			Serializable id,
			SessionFactoryImplementor factory,
			StringBuilder s ) {
		// Need to use the identifier type of the collection owner
		// since the incoming is value is actually the owner's id.
		// Using the collection's key type causes problems with
		// property-ref keys.
		// Also need to check that the expected identifier type matches
		// the given ID.  Due to property-ref keys, the collection key
		// may not be the owner key.
		Type ownerIdentifierType = persister.getOwnerEntityPersister()
				.getIdentifierType();
		if ( id.getClass().isAssignableFrom( 
				ownerIdentifierType.getReturnedClass() ) ) {
			s.append( ownerIdentifierType.toLoggableString( id, factory ) );
		}
		else {
			// TODO: This is a crappy backup if a property-ref is used.
			// If the reference is an object w/o toString(), this isn't going to work.
			s.append( id.toString() );
		}
	}
