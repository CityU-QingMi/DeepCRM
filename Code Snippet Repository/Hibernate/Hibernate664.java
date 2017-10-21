	@Override
	public void addPropertyAnnotatedWithMapsIdSpecj(XClass entityType, PropertyData property, String mapsIdValue) {
		if ( propertiesAnnotatedWithMapsId == null ) {
			propertiesAnnotatedWithMapsId = new HashMap<XClass, Map<String, PropertyData>>();
		}

		Map<String, PropertyData> map = propertiesAnnotatedWithMapsId.get( entityType );
		if ( map == null ) {
			map = new HashMap<String, PropertyData>();
			propertiesAnnotatedWithMapsId.put( entityType, map );
		}
		map.put( mapsIdValue, property );
	}
