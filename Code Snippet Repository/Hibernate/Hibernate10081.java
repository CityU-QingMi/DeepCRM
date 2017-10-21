	@Override
	public CrossTypeRevisionChangesReader getCrossTypeRevisionChangesReader() throws AuditException {
		if ( !enversService.getGlobalConfiguration().isTrackEntitiesChangedInRevision() ) {
			throw new AuditException(
					"This API is designed for Envers default mechanism of tracking entities modified in a given revision."
							+ " Extend DefaultTrackingModifiedEntitiesRevisionEntity, utilize @ModifiedEntityNames annotation or set "
							+ "'org.hibernate.envers.track_entities_changed_in_revision' parameter to true."
			);
		}
		return crossTypeRevisionChangesReader;
	}
