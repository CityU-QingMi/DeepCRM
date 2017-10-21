	@SuppressWarnings({ "" })
	public static Map clone(Map<?,?> configurationValues) {
		if ( configurationValues == null ) {
			return null;
		}
		// If a Properties object, leverage its clone() impl
		if ( Properties.class.isInstance( configurationValues ) ) {
			return (Properties) ( (Properties) configurationValues ).clone();
		}
		// Otherwise make a manual copy
		HashMap clone = new HashMap();
		for ( Map.Entry entry : configurationValues.entrySet() ) {
			clone.put( entry.getKey(), entry.getValue() );
		}
		return clone;
	}
