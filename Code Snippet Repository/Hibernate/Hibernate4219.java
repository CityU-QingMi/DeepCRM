	public static String infoString(
			EntityPersister persister, 
			Object id, 
			Type identifierType,
			SessionFactoryImplementor factory) {
		StringBuilder s = new StringBuilder();
		s.append( '[' );
		if( persister == null ) {
			s.append( "<null EntityPersister>" );
		}
		else {
			s.append( persister.getEntityName() );
		}
		s.append( '#' );

		if ( id == null ) {
			s.append( "<null>" );
		}
		else {
			s.append( identifierType.toLoggableString( id, factory ) );
		}
		s.append( ']' );

		return s.toString();
	}
