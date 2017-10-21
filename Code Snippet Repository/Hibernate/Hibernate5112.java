	private Type requireIdentifierOrUniqueKeyType(Mapping mapping) {
		final Type fkTargetType = getIdentifierOrUniqueKeyType( mapping );
		if ( fkTargetType == null ) {
			throw new MappingException(
					"Unable to determine FK target Type for many-to-one mapping: " +
							"referenced-entity-name=[" + getAssociatedEntityName() +
							"], referenced-entity-attribute-name=[" + getLHSPropertyName() + "]"
			);
		}
		return fkTargetType;
	}
