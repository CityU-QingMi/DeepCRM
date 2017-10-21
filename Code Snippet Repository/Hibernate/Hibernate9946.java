	private Collection<RelationDescription> getRelationDescriptions(String entityName) {
		final EntityConfiguration entCfg = entitiesConfigurations.get( entityName );
		Collection<RelationDescription> descriptions = new ArrayList<>();
		if ( entCfg.getParentEntityName() != null ) {
			// collect descriptions from super classes
			descriptions.addAll( getRelationDescriptions( entCfg.getParentEntityName() ) );
		}
		for ( RelationDescription relationDescription : entCfg.getRelationsIterator() ) {
			descriptions.add( relationDescription );
		}
		return descriptions;
	}
