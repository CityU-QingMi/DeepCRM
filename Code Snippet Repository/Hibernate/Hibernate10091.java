	public DefaultTrackingModifiedEntitiesRevisionInfoGenerator(
			String revisionInfoEntityName,
			Class<?> revisionInfoClass,
			Class<? extends RevisionListener> listenerClass,
			PropertyData revisionInfoTimestampData,
			boolean timestampAsDate,
			PropertyData modifiedEntityNamesData,
			ServiceRegistry serviceRegistry) {
		super( revisionInfoEntityName, revisionInfoClass, listenerClass, revisionInfoTimestampData, timestampAsDate, serviceRegistry );
		modifiedEntityNamesSetter = ReflectionTools.getSetter( revisionInfoClass, modifiedEntityNamesData, serviceRegistry );
		modifiedEntityNamesGetter = ReflectionTools.getGetter( revisionInfoClass, modifiedEntityNamesData, serviceRegistry );
	}
