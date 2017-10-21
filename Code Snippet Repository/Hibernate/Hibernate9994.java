	@Override
	public IdMapper prefixMappedProperties(String prefix) {
		final EmbeddedIdMapper ret = new EmbeddedIdMapper( idPropertyData, compositeIdClass, getServiceRegistry() );

		for ( PropertyData propertyData : ids.keySet() ) {
			final String propertyName = propertyData.getName();
			ret.ids.put( propertyData, new SingleIdMapper( getServiceRegistry(), new PropertyData( prefix + propertyName, propertyData ) ) );
		}

		return ret;
	}
