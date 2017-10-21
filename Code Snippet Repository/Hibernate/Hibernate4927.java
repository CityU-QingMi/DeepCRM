	public static IdentifierProperty buildIdentifierAttribute(
			PersistentClass mappedEntity,
			IdentifierGenerator generator) {
		String mappedUnsavedValue = mappedEntity.getIdentifier().getNullValue();
		Type type = mappedEntity.getIdentifier().getType();
		Property property = mappedEntity.getIdentifierProperty();

		IdentifierValue unsavedValue = UnsavedValueFactory.getUnsavedIdentifierValue(
				mappedUnsavedValue,
				getGetter( property ),
				type,
				getConstructor( mappedEntity )
		);

		if ( property == null ) {
			// this is a virtual id property...
			return new IdentifierProperty(
					type,
					mappedEntity.hasEmbeddedIdentifier(),
					mappedEntity.hasIdentifierMapper(),
					unsavedValue,
					generator
			);
		}
		else {
			return new IdentifierProperty(
					property.getName(),
					type,
					mappedEntity.hasEmbeddedIdentifier(),
					unsavedValue,
					generator
			);
		}
	}
