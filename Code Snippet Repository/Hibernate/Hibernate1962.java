	public static String[] getRHSColumnNames(AssociationType type, SessionFactoryImplementor factory) {
		final String uniqueKeyPropertyName = type.getRHSUniqueKeyPropertyName();
		final Joinable joinable = type.getAssociatedJoinable( factory );
		if ( uniqueKeyPropertyName == null ) {
			return joinable.getKeyColumnNames();
		}
		else {
			return ( (OuterJoinLoadable) joinable ).getPropertyColumnNames( uniqueKeyPropertyName );
		}
	}
