	private static AuditStrategy initializeAuditStrategy(
			String auditStrategyName,
			Class<?> revisionInfoClass,
			PropertyData revisionInfoTimestampData,
			ServiceRegistry serviceRegistry) {
		AuditStrategy strategy;

		try {
			final Class<?> auditStrategyClass = loadClass( auditStrategyName, serviceRegistry );
			strategy = (AuditStrategy) ReflectHelper.getDefaultConstructor( auditStrategyClass ).newInstance();
		}
		catch (Exception e) {
			throw new MappingException(
					String.format( "Unable to create AuditStrategy [%s] instance.", auditStrategyName ),
					e
			);
		}

		if ( strategy instanceof ValidityAuditStrategy ) {
			// further initialization required
			final Getter revisionTimestampGetter = ReflectionTools.getGetter(
					revisionInfoClass,
					revisionInfoTimestampData,
					serviceRegistry
			);
			( (ValidityAuditStrategy) strategy ).setRevisionTimestampGetter( revisionTimestampGetter );
		}

		return strategy;
	}
