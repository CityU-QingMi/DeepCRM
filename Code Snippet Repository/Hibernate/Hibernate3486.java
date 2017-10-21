	protected boolean isJoinedFetchEnabledInMapping(FetchMode config, AssociationType type)
			throws MappingException {
		if ( !type.isEntityType() && !type.isCollectionType() ) {
			return false;
		}
		else {
			if ( config == FetchMode.JOIN ) {
				return true;
			}
			if ( config == FetchMode.SELECT ) {
				return false;
			}
			if ( type.isEntityType() ) {
				//TODO: look at the owning property and check that it 
				//      isn't lazy (by instrumentation)
				EntityType entityType = (EntityType) type;
				EntityPersister persister = getFactory().getEntityPersister( entityType.getAssociatedEntityName() );
				return !persister.hasProxy();
			}
			else {
				return false;
			}
		}
	}
