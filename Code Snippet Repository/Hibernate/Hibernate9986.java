	@Override
	public boolean hasPropertiesWithModifiedFlag() {
		// checks all properties, exposed both by the main mapper and parent mapper.
		for ( PropertyData property : getProperties().keySet() ) {
			if ( property.isUsingModifiedFlag() ) {
				return true;
			}
		}
		return false;
	}
