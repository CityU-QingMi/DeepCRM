	public ComponentPropertyMapper(PropertyData propertyData, Class componentClass) {
		this.propertyData = propertyData;
		//if class is a map it means that this is dynamic component
		if ( Map.class.isAssignableFrom( componentClass ) ) {
			this.delegate = new MultiDynamicComponentMapper( propertyData );
			this.componentClass = HashMap.class;
		}
		else {
			this.delegate = new MultiPropertyMapper();
			this.componentClass = componentClass;
		}
	}
