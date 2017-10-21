	public static Setter getSetter(Class cls, String propertyName, String accessorType, ServiceRegistry serviceRegistry) {
		final Pair<Class, String> key = Pair.make( cls, propertyName );
		Setter value = SETTER_CACHE.get( key );
		if ( value == null ) {
			value = getAccessStrategy( cls, serviceRegistry, accessorType ).buildPropertyAccess( cls, propertyName ).getSetter();
			// It's ok if two setters are generated concurrently
			SETTER_CACHE.put( key, value );
		}

		return value;
	}
