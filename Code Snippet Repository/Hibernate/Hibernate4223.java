	public static String collectionInfoString( 
			CollectionPersister persister,
			PersistentCollection collection,
			Serializable collectionKey,
			SharedSessionContractImplementor session ) {
		
		StringBuilder s = new StringBuilder();
		s.append( '[' );
		if ( persister == null ) {
			s.append( "<unreferenced>" );
		}
		else {
			s.append( persister.getRole() );
			s.append( '#' );
			
			Type ownerIdentifierType = persister.getOwnerEntityPersister()
					.getIdentifierType();
			Serializable ownerKey;
			// TODO: Is it redundant to attempt to use the collectionKey,
			// or is always using the owner id sufficient?
			if ( collectionKey.getClass().isAssignableFrom( 
					ownerIdentifierType.getReturnedClass() ) ) {
				ownerKey = collectionKey;
			}
			else {
				Object collectionOwner = collection == null ? null : collection.getOwner();
				EntityEntry entry = collectionOwner == null ? null : session.getPersistenceContext().getEntry(collectionOwner);
				ownerKey = entry == null ? null : entry.getId();
			}
			s.append( ownerIdentifierType.toLoggableString( 
					ownerKey, session.getFactory() ) );
		}
		s.append( ']' );

		return s.toString();
	}
