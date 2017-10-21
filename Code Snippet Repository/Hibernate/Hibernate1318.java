	public static void verifyProperties(Map<?,?> configurationValues) {
		final Map propertiesToAdd = new HashMap();
		for ( Map.Entry entry : configurationValues.entrySet() ) {
			final Object replacementKey = OBSOLETE_PROPERTIES.get( entry.getKey() );
			if ( replacementKey != null ) {
				LOG.unsupportedProperty( entry.getKey(), replacementKey );
			}
			final Object renamedKey = RENAMED_PROPERTIES.get( entry.getKey() );
			if ( renamedKey != null ) {
				LOG.renamedProperty( entry.getKey(), renamedKey );
				propertiesToAdd.put( renamedKey, entry.getValue() );
			}
		}
		configurationValues.putAll( propertiesToAdd );
	}
