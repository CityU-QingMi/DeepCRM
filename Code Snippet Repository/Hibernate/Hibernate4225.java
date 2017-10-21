	public static String collectionInfoString(
			CollectionPersister persister, 
			Serializable id, 
			SessionFactoryImplementor factory) {
		StringBuilder s = new StringBuilder();
		s.append( '[' );
		if ( persister == null ) {
			s.append( "<unreferenced>" );
		}
		else {
			s.append( persister.getRole() );
			s.append( '#' );

			if ( id == null ) {
				s.append( "<null>" );
			}
			else {
				addIdToCollectionInfoString( persister, id, factory, s );
			}
		}
		s.append( ']' );

		return s.toString();
	}
