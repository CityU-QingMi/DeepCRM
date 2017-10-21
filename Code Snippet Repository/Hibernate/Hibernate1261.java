	@Override
	protected AttributeConversionInfo locateAttributeConversionInfo(String path) {
		final String key = removePrefix( path, "key" );
		if ( key != null ) {
			return keyAttributeConversionInfoMap.get( key );
		}

		final String element = removePrefix( path, "element" );
		if ( element != null ) {
			return elementAttributeConversionInfoMap.get( element );
		}

		return elementAttributeConversionInfoMap.get( path );
	}
