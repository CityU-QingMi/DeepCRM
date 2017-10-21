	private void generateBidirectionRelationInfo() {
		// Checking each relation if it is bidirectional. If so, storing that information.
		for ( Map.Entry<String, EntityConfiguration> entry : entitiesConfigurations.entrySet() ) {
			final String entityName = entry.getKey();
			final EntityConfiguration entCfg = entry.getValue();
			// Iterating over all relations from that entity
			for ( RelationDescription relDesc : entCfg.getRelationsIterator() ) {
				// If this is an "owned" relation, checking the related entity, if it has a relation that has
				// a mapped-by attribute to the currently checked. If so, this is a bidirectional relation.
				if ( relDesc.getRelationType() == RelationType.TO_ONE ||
						relDesc.getRelationType() == RelationType.TO_MANY_MIDDLE ) {
					final EntityConfiguration entityConfiguration = entitiesConfigurations.get( relDesc.getToEntityName() );
					if ( entityConfiguration != null ) {
						for ( RelationDescription other : entityConfiguration.getRelationsIterator() ) {
							if ( relDesc.getFromPropertyName().equals( other.getMappedByPropertyName() ) &&
									(entityName.equals( other.getToEntityName() )) ) {
								relDesc.setBidirectional( true );
								other.setBidirectional( true );
							}
						}
					}
				}
			}
		}
	}
