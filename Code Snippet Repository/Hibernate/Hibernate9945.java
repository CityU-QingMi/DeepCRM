	public RelationDescription getRelationDescription(String entityName, String propertyName) {
		final EntityConfiguration entCfg;
		if ( isVersioned( entityName ) ) {
			entCfg = get( entityName );
		}
		else {
			entCfg = getNotVersionEntityConfiguration( entityName );
		}
		final RelationDescription relDesc = entCfg.getRelationDescription( propertyName );
		if ( relDesc != null ) {
			return relDesc;
		}
		else if ( entCfg.getParentEntityName() != null ) {
			// The field may be declared in a superclass ...
			return getRelationDescription( entCfg.getParentEntityName(), propertyName );
		}
		else {
			return null;
		}
	}
