	protected void addAll( Properties target, Map source ) {
		Iterator itr = source.entrySet().iterator();
		while ( itr.hasNext() ) {
			final Map.Entry entry = ( Map.Entry ) itr.next();
			final String propertyName = ( String ) entry.getKey();
			final String propertyValue = ( String ) entry.getValue();
			if ( propertyName != null && propertyValue != null ) {
				// Make sure we don't override previous set values
				if ( !target.keySet().contains( propertyName ) ) {
					if ( !getExcludedPropertyNames().contains( propertyName) ) {
						target.put( propertyName, propertyValue );
					}
				}
			}
		}
	}
