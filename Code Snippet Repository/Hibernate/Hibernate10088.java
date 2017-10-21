	public DefaultRevisionInfoGenerator(
			String revisionInfoEntityName,
			Class<?> revisionInfoClass,
			Class<? extends RevisionListener> listenerClass,
			PropertyData revisionInfoTimestampData,
			boolean timestampAsDate,
			ServiceRegistry serviceRegistry) {
		this.revisionInfoEntityName = revisionInfoEntityName;
		this.revisionInfoClass = revisionInfoClass;
		this.timestampAsDate = timestampAsDate;

		revisionTimestampSetter = ReflectionTools.getSetter( revisionInfoClass, revisionInfoTimestampData, serviceRegistry );

		if ( !listenerClass.equals( RevisionListener.class ) ) {
			// This is not the default value.
			try {
				listener = (RevisionListener) ReflectHelper.getDefaultConstructor( listenerClass ).newInstance();
			}
			catch (InstantiationException e) {
				throw new MappingException( e );
			}
			catch (IllegalAccessException e) {
				throw new MappingException( e );
			}
			catch (InvocationTargetException e) {
				throw new MappingException( e );
			}
		}
		else {
			// Default listener - none
			listener = null;
		}

		sessionCacheCleaner = new SessionCacheCleaner();
	}
