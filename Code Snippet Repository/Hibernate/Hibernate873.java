	public FilterSourceImpl(
			MappingDocument mappingDocument,
			JaxbHbmFilterType filterElement) {
		super( mappingDocument );
		this.name = filterElement.getName();

		String explicitAutoAliasInjectionSetting = filterElement.getAutoAliasInjection();

		String conditionAttribute = filterElement.getCondition();
		String conditionContent = null;

		for ( Serializable content : filterElement.getContent() ) {
			if ( String.class.isInstance( content ) ) {
				final String str = content.toString();
				if ( !StringHelper.isEmptyOrWhiteSpace( str ) ) {
					conditionContent = str.trim();
				}
			}
			else {
				final JaxbHbmFilterAliasMappingType aliasMapping = JaxbHbmFilterAliasMappingType.class.cast( content );
				if ( StringHelper.isNotEmpty( aliasMapping.getTable() ) ) {
					aliasTableMap.put( aliasMapping.getAlias(), aliasMapping.getTable() );
				}
				else if ( StringHelper.isNotEmpty( aliasMapping.getEntity() ) ) {
					aliasEntityMap.put( aliasMapping.getAlias(), aliasMapping.getTable() );
				}
				else {
					throw new MappingException(
							"filter alias must define either table or entity attribute",
							mappingDocument.getOrigin()
					);
				}
			}
		}

		this.condition = Helper.coalesce( conditionContent, conditionAttribute );
		this.autoAliasInjection = StringHelper.isNotEmpty( explicitAutoAliasInjectionSetting )
				? Boolean.valueOf( explicitAutoAliasInjectionSetting )
				: true;
	}
