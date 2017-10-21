	private boolean hasNonIdentifierPropertyNamedId(final EntityType entityType, final Mapping factory) {
		// TODO : would be great to have a Mapping#hasNonIdentifierPropertyNamedId method
		// I don't believe that Mapping#getReferencedPropertyType accounts for the identifier property; so
		// if it returns for a property named 'id', then we should have a non-id field named id
		try {
			return factory.getReferencedPropertyType(
					entityType.getAssociatedEntityName(),
					EntityPersister.ENTITY_ID
			) != null;
		}
		catch (MappingException e) {
			return false;
		}
	}
