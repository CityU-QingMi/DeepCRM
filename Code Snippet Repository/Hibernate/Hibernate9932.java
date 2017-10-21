	private RelationDescription searchForRelationDescription(String entityName, String referencingPropertyName) {
		final EntityConfiguration configuration = getEnversService().getEntitiesConfigurations().get( entityName );
		final String propertyName = sanitizeReferencingPropertyName( referencingPropertyName );
		final RelationDescription rd = configuration.getRelationDescription( propertyName );
		if ( rd == null && configuration.getParentEntityName() != null ) {
			return searchForRelationDescription( configuration.getParentEntityName(), propertyName );
		}

		return rd;
	}
