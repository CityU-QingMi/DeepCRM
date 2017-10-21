	Ejb3Column[] overrideColumnFromMapperOrMapsIdProperty(boolean isId) {
		Ejb3Column[] result = columns;
		final PropertyData overridingProperty = BinderHelper.getPropertyOverriddenByMapperOrMapsId(
				isId,
				propertyHolder,
				property.getName(),
				buildingContext
		);
		if ( overridingProperty != null ) {
			result = buildExcplicitOrDefaultJoinColumn( overridingProperty );
		}
		return result;
	}
