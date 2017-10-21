	public static EntityIdentifierDefinition buildSimpleEncapsulatedIdentifierDefinition(final AbstractEntityPersister entityPersister) {
		return new EncapsulatedEntityIdentifierDefinition() {
			private final AttributeDefinitionAdapter attr = new AttributeDefinitionAdapter( entityPersister);

			@Override
			public AttributeDefinition getAttributeDefinition() {
				return attr;
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
