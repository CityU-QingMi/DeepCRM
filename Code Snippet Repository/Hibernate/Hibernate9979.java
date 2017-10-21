	@Override
	public void mapModifiedFlagsToMapFromEntity(
			SessionImplementor session,
			Map<String, Object> data,
			Object newObj,
			Object oldObj) {
		// Synthetic properties are not subject to withModifiedFlag analysis
		if ( propertyData.isUsingModifiedFlag() && !propertyData.isSynthetic() ) {
			data.put( propertyData.getModifiedFlagPropertyName(), !EqualsHelper.areEqual( newObj, oldObj ) );
		}
	}
