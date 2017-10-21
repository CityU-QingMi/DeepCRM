	@Override
	public Map<String, Object> generateData(Object revisionData) {
		fillDataWithId( data, revisionData );
		final Map<String, Object> preGenerateData = new HashMap<>( data );

		final EntityConfiguration entityConfig = enversService.getEntitiesConfigurations().get( getEntityName() );
		final PropertyMapper propertyMapper = entityConfig.getPropertyMapper();
		// HHH-7681 - Use entity as 'oldObj' so fake bidirectional non-insertable fields are tracked properly.
		propertyMapper.mapToMapFromEntity( sessionImplementor, data, entity, entity );
		propertyMapper.mapModifiedFlagsToMapFromEntity( sessionImplementor, data, entity, entity );
		propertyMapper.mapModifiedFlagsToMapForCollectionChange( collectionPropertyName, data );

		data.putAll( preGenerateData );
		return data;
	}
