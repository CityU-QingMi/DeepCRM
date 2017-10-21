	private Map filter(Map propertyResults) {
		Map result = new HashMap( propertyResults.size() );

		String keyPrefix = "element.";

		Iterator iter = propertyResults.entrySet().iterator();
		while ( iter.hasNext() ) {
			Map.Entry element = ( Map.Entry ) iter.next();
			String path = ( String ) element.getKey();
			if ( path.startsWith( keyPrefix ) ) {
				result.put( path.substring( keyPrefix.length() ), element.getValue() );
			}
		}

		return result;
	}
