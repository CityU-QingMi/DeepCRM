	public static Map generateSettings(Object... keysAndValues) {
		final Map settings = new HashMap();

		if ( keysAndValues != null ) {
			if ( keysAndValues.length %2 != 0 ) {
				Assert.fail( "Varargs to create settings should contain even number of entries" );
			}


			for ( int i = 0; i < keysAndValues.length; ) {
				settings.put( keysAndValues[i], keysAndValues[i+1] );
				i+=2;
			}
		}

		return settings;
	}
