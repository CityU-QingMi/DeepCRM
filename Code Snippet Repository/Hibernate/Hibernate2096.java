	private boolean hikariConfigDefined(Map configValues) {
		for ( Object key : configValues.keySet() ) {
			if ( !String.class.isInstance( key ) ) {
				continue;
			}

			if ( ( (String) key ).startsWith( "hibernate.hikari." ) ) {
				return true;
			}
		}
		return false;
	}
