	private MiddleComponentData addIndex(Element middleEntityXml, QueryGeneratorBuilder queryGeneratorBuilder) {
		if ( propertyValue instanceof IndexedCollection ) {
			final IndexedCollection indexedValue = (IndexedCollection) propertyValue;
			final String mapKey = propertyAuditingData.getMapKey();
			if ( mapKey == null ) {
				// This entity doesn't specify a javax.persistence.MapKey. Mapping it to the middle entity.
				return addValueToMiddleTable(
						indexedValue.getIndex(),
						middleEntityXml,
						queryGeneratorBuilder,
						"mapkey",
						null,
						true
				);
			}
			else {
				final IdMappingData referencedIdMapping = mainGenerator.getEntitiesConfigurations()
						.get( referencedEntityName ).getIdMappingData();
				final int currentIndex = queryGeneratorBuilder == null ? 0 : queryGeneratorBuilder.getCurrentIndex();
				if ( "".equals( mapKey ) ) {
					// The key of the map is the id of the entity.
					return new MiddleComponentData(
							new MiddleMapKeyIdComponentMapper(
									mainGenerator.getVerEntCfg(),
									referencedIdMapping.getIdMapper()
							),
							currentIndex
					);
				}
				else {
					// The key of the map is a property of the entity.
					return new MiddleComponentData(
							new MiddleMapKeyPropertyComponentMapper(
									mapKey,
									propertyAuditingData.getAccessType()
							),
							currentIndex
					);
				}
			}
		}
		else {
			// No index - creating a dummy mapper.
			return new MiddleComponentData( new MiddleDummyComponentMapper(), 0 );
		}
	}
