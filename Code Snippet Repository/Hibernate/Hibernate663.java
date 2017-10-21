	@Override
	public void addPropertyAnnotatedWithMapsId(XClass entityType, PropertyData property) {
		if ( propertiesAnnotatedWithMapsId == null ) {
			propertiesAnnotatedWithMapsId = new HashMap<XClass, Map<String, PropertyData>>();
		}

		Map<String, PropertyData> map = propertiesAnnotatedWithMapsId.get( entityType );
		if ( map == null ) {
			map = new HashMap<String, PropertyData>();
			propertiesAnnotatedWithMapsId.put( entityType, map );
		}
		map.put( property.getProperty().getAnnotation( MapsId.class ).value(), property );
	}
