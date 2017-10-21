	IdMappingData getReferencedIdMappingData(
			String entityName, String referencedEntityName,
			PropertyAuditingData propertyAuditingData,
			boolean allowNotAuditedTarget) {
		EntityConfiguration configuration = getEntitiesConfigurations().get( referencedEntityName );
		if ( configuration == null ) {
			final RelationTargetAuditMode relationTargetAuditMode = propertyAuditingData.getRelationTargetAuditMode();
			configuration = getNotAuditedEntitiesConfigurations().get( referencedEntityName );

			if ( configuration == null || !allowNotAuditedTarget || !RelationTargetAuditMode.NOT_AUDITED.equals(
					relationTargetAuditMode
			) ) {
				throw new MappingException(
						"An audited relation from " + entityName + "."
								+ propertyAuditingData.getName() + " to a not audited entity " + referencedEntityName + "!"
								+ (allowNotAuditedTarget ?
								" Such mapping is possible, but has to be explicitly defined using @Audited(targetAuditMode = NOT_AUDITED)." :
								"")
				);
			}
		}

		return configuration.getIdMappingData();
	}
