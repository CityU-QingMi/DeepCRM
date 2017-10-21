	public static RelationDescription getRelatedEntity(
			EnversService enversService,
			String entityName,
			String propertyName) throws AuditException {
		RelationDescription relationDesc = enversService.getEntitiesConfigurations().getRelationDescription( entityName, propertyName );

		if ( relationDesc == null ) {
			return null;
		}

		if ( relationDesc.getRelationType() == RelationType.TO_ONE ) {
			return relationDesc;
		}

		throw new AuditException(
				"This type of relation (" + entityName + "." + propertyName +
						") isn't supported and can't be used in queries."
		);
	}
