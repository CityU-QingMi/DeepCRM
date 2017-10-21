	public static String collectionInfoString(
			CollectionPersister persister, 
			Serializable[] ids, 
			SessionFactoryImplementor factory) {
		StringBuilder s = new StringBuilder();
		s.append( '[' );
		if ( persister == null ) {
			s.append( "<unreferenced>" );
		}
		else {
			s.append( persister.getRole() );
			s.append( "#<" );
			for ( int i = 0; i < ids.length; i++ ) {
				addIdToCollectionInfoString( persister, ids[i], factory, s );
				if ( i < ids.length-1 ) {
					s.append( ", " );
				}
			}
			s.append( '>' );
		}
		s.append( ']' );
		return s.toString();
	}
