	@Override
	public void addTypeDefinition(TypeDefinition typeDefinition) {
		if ( typeDefinition == null ) {
			throw new IllegalArgumentException( "Type definition is null" );
		}

		// Need to register both by name and registration keys.
		if ( !StringHelper.isEmpty( typeDefinition.getName() ) ) {
			addTypeDefinition( typeDefinition.getName(), typeDefinition );
		}

		if ( typeDefinition.getRegistrationKeys() != null ) {
			for ( String registrationKey : typeDefinition.getRegistrationKeys() ) {
				addTypeDefinition( registrationKey, typeDefinition );
			}
		}
	}
