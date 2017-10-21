	public static void checkPropertyNotARelation(
			EnversService enversService,
			String entityName,
			String propertyName) throws AuditException {
		if ( enversService.getEntitiesConfigurations().get( entityName ).isRelation( propertyName ) ) {
			throw new AuditException(
					"This criterion cannot be used on a property that is " +
							"a relation to another property."
			);
		}
	}
