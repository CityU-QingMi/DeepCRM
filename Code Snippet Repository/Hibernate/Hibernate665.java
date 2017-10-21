	@Override
	public void addToOneAndIdProperty(XClass entityType, PropertyData property) {
		if ( propertiesAnnotatedWithIdAndToOne == null ) {
			propertiesAnnotatedWithIdAndToOne = new HashMap<XClass, Map<String, PropertyData>>();
		}

		Map<String, PropertyData> map = propertiesAnnotatedWithIdAndToOne.get( entityType );
		if ( map == null ) {
			map = new HashMap<String, PropertyData>();
			propertiesAnnotatedWithIdAndToOne.put( entityType, map );
		}
		map.put( property.getPropertyName(), property );
	}
