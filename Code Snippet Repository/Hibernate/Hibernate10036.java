	protected void addMiddleEqualToQuery(
			CompositeMapperBuilder compositeMapper,
			Parameters parameters,
			String idPrefix1,
			String prefix1,
			String idPrefix2,
			String prefix2) {
		for ( final Map.Entry<PropertyData, PropertyMapper> entry : compositeMapper.getProperties().entrySet() ) {
			final String propertyName = entry.getKey().getName();
			final PropertyMapper nestedMapper = entry.getValue();
			if ( nestedMapper instanceof CompositeMapperBuilder ) {
				addMiddleEqualToQuery(
						(CompositeMapperBuilder) nestedMapper,
						parameters,
						idPrefix1,
						prefix1,
						idPrefix2,
						prefix2
				);
			}
			else if ( nestedMapper instanceof ToOneIdMapper ) {
				( (ToOneIdMapper) nestedMapper ).addMiddleEqualToQuery(
						parameters,
						idPrefix1,
						prefix1,
						idPrefix2,
						prefix2
				);
			}
			else {
				parameters.addWhereOrNullRestriction(
						prefix1 + '.' + propertyName,
						false,
						"=",
						prefix2 + '.' + propertyName, false
				);
			}
		}
	}
