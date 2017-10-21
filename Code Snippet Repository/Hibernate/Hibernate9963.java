	@Override
	public void mapModifiedFlagsToMapForCollectionChange(String collectionPropertyName, Map<String, Object> data) {
		if ( propertyData.isUsingModifiedFlag() ) {
			boolean hasModifiedCollection = false;
			for ( PropertyData propData : delegate.getProperties().keySet() ) {
				if ( collectionPropertyName.equals( propData.getName() ) ) {
					hasModifiedCollection = true;
					break;
				}
			}
			data.put( propertyData.getModifiedFlagPropertyName(), hasModifiedCollection );
		}
	}
