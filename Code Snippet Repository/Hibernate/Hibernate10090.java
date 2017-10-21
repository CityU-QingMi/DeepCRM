	@Override
	public void entityChanged(
			Class entityClass,
			String entityName,
			Serializable entityId,
			RevisionType revisionType,
			Object revisionInfo) {
		if ( listener instanceof EntityTrackingRevisionListener ) {
			( (EntityTrackingRevisionListener) listener ).entityChanged(
					entityClass,
					entityName,
					entityId,
					revisionType,
					revisionInfo
			);
		}
	}
