	@Override
	public PropertyAccess buildPropertyAccess(Class containerJavaType, String propertyName) {
		for ( PropertyAccessStrategy candidate : chain ) {
			try {
				return candidate.buildPropertyAccess( containerJavaType, propertyName );
			}
			catch (Exception ignore) {
				// ignore
			}
		}

		throw new PropertyNotFoundException( "Could not resolve PropertyAccess for " + propertyName + " on " + containerJavaType );
	}
