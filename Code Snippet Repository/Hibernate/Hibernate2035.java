	@Override
	public void replaceDelayedEntityIdentityInsertKeys(EntityKey oldKey, Serializable generatedId) {
		final Object entity = entitiesByKey.remove( oldKey );
		final EntityEntry oldEntry = entityEntryContext.removeEntityEntry( entity );
		parentsByChild.clear();

		final EntityKey newKey = session.generateEntityKey( generatedId, oldEntry.getPersister() );
		addEntity( newKey, entity );
		addEntry(
				entity,
				oldEntry.getStatus(),
				oldEntry.getLoadedState(),
				oldEntry.getRowId(),
				generatedId,
				oldEntry.getVersion(),
				oldEntry.getLockMode(),
				oldEntry.isExistsInDatabase(),
				oldEntry.getPersister(),
				oldEntry.isBeingReplicated()
		);
	}
