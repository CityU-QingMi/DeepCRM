	@Override
	public Column[] getOverriddenColumn(String propertyName) {
		//FIXME this is yukky
		Column[] result = super.getOverriddenColumn( propertyName );
		if ( result == null ) {
			String userPropertyName = extractUserPropertyName( "id", propertyName );
			if ( userPropertyName != null ) result = super.getOverriddenColumn( userPropertyName );
		}
		if ( result == null ) {
			String userPropertyName = extractUserPropertyName( PropertyPath.IDENTIFIER_MAPPER_PROPERTY, propertyName );
			if ( userPropertyName != null ) result = super.getOverriddenColumn( userPropertyName );
		}
		return result;
	}
