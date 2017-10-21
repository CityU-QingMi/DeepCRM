	@Override
	public String toMetaDataObjectName(Identifier identifier) {
		log.tracef( "Normalizing identifier quoting for object name [%s]", identifier );

		if ( identifier == null ) {
			// if this method was called, the value is needed
			throw new IllegalArgumentException( "null was passed as an object name" );
		}
		return toMetaDataText( identifier );
	}
