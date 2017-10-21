	public static String infoString(
			EntityPersister persister,
			Object id, 
			SessionFactoryImplementor factory) {
		StringBuilder s = new StringBuilder();
		s.append( '[' );
		Type idType;
		if( persister == null ) {
			s.append( "<null EntityPersister>" );
			idType = null;
		}
		else {
			s.append( persister.getEntityName() );
			idType = persister.getIdentifierType();
		}
		s.append( '#' );

		if ( id == null ) {
			s.append( "<null>" );
		}
		else {
			if ( idType == null ) {
				s.append( id );
			}
			else {
				if ( factory != null ) {
					s.append( idType.toLoggableString( id, factory ) );
				}
				else {
					s.append( "<not loggable>" );
				}
			}
		}
		s.append( ']' );

		return s.toString();

	}
