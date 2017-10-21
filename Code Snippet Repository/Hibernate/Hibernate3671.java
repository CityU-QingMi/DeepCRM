	private EntityLoader(
			SessionFactoryImplementor factory,
			OuterJoinLoadable persister,
			String[] uniqueKeyColumnNames,
			Type uniqueKeyType,
			QueryBuildingParameters buildingParameters) throws MappingException {
		super( persister, factory, uniqueKeyColumnNames, uniqueKeyType, buildingParameters );
		if ( log.isDebugEnabled() ) {
			if ( buildingParameters.getLockOptions() != null ) {
				log.debugf(
						"Static select for entity %s [%s:%s]: %s",
						getEntityName(),
						buildingParameters.getLockOptions().getLockMode(),
						buildingParameters.getLockOptions().getTimeOut(),
						getStaticLoadQuery().getSqlStatement()
				);
			}
			else if ( buildingParameters.getLockMode() != null ) {
				log.debugf(
						"Static select for entity %s [%s]: %s",
						getEntityName(),
						buildingParameters.getLockMode(),
						getStaticLoadQuery().getSqlStatement()
				);
			}
		}
	}
