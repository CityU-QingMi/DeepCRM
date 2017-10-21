	public static EntityIdentifierDefinition buildEncapsulatedCompositeIdentifierDefinition(
			final AbstractEntityPersister entityPersister) {

		return new EncapsulatedEntityIdentifierDefinition() {
			private final CompositionDefinitionAdapter compositionDefinition = new CompositionDefinitionAdapter( entityPersister );

			@Override
			public AttributeDefinition getAttributeDefinition() {
				return compositionDefinition;
			}

			@Override
			public boolean isEncapsulated() {
				return true;
			}

			@Override
			public EntityDefinition getEntityDefinition() {
				return entityPersister;
			}
		};
	}
