	public String toLoggableString(SharedSessionContractImplementor session) {
		final StringBuilder sb = new StringBuilder( getClass().getSimpleName() ).append( '[' );
		if ( propertyPathsByTransientEntity != null ) {
			for ( Map.Entry<Object,Set<String>> entry : propertyPathsByTransientEntity.entrySet() ) {
				sb.append( "transientEntityName=" ).append( session.bestGuessEntityName( entry.getKey() ) );
				sb.append( " requiredBy=" ).append( entry.getValue() );
			}
		}
		sb.append( ']' );
		return sb.toString();
	}
