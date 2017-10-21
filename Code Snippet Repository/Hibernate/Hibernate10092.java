	@Override
	@SuppressWarnings({""})
	public void entityChanged(
			Class entityClass,
			String entityName,
			Serializable entityId,
			RevisionType revisionType,
			Object revisionEntity) {
		super.entityChanged( entityClass, entityName, entityId, revisionType, revisionEntity );
		Set<String> modifiedEntityNames = (Set<String>) modifiedEntityNamesGetter.get( revisionEntity );
		if ( modifiedEntityNames == null ) {
			modifiedEntityNames = new HashSet<>();
			modifiedEntityNamesSetter.set( revisionEntity, modifiedEntityNames, null );
		}
		modifiedEntityNames.add( entityName );
	}
