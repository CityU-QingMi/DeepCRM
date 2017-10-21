	private void addValueInFirstPass(
			Element parent,
			Value value,
			CompositeMapperBuilder currentMapper,
			String entityName,
			EntityXmlMappingData xmlMappingData,
			PropertyAuditingData propertyAuditingData,
			boolean insertable,
			boolean processModifiedFlag) {
		final Type type = value.getType();
		final boolean isBasic = basicMetadataGenerator.addBasic(
				parent,
				propertyAuditingData,
				value,
				currentMapper,
				insertable,
				false
		);

		if ( isBasic ) {
			// The property was mapped by the basic generator.
		}
		else if ( type instanceof ComponentType ) {
			componentMetadataGenerator.addComponent(
					parent, propertyAuditingData, value, currentMapper,
					entityName, xmlMappingData, true
			);
		}
		else {
			if ( !processedInSecondPass( type ) ) {
				// If we got here in the first pass, it means the basic mapper didn't map it, and none of the
				// above branches either.
				throwUnsupportedTypeException( type, entityName, propertyAuditingData.getName() );
			}
			return;
		}
		addModifiedFlagIfNeeded( parent, propertyAuditingData, processModifiedFlag );
	}
