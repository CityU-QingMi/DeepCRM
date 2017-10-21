	private void addTypeDefinition(String registrationKey, TypeDefinition typeDefinition) {
		final TypeDefinition previous = typeDefinitionMap.put(
				registrationKey, typeDefinition );
		if ( previous != null ) {
			log.debugf(
					"Duplicate typedef name [%s] now -> %s",
					registrationKey,
					typeDefinition.getTypeImplementorClass().getName()
			);
		}
	}
