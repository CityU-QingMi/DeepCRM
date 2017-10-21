	public static Getter getGetter(Class cls, String propertyName, String accessorType, ServiceRegistry serviceRegistry) {
		final Pair<Class, String> key = Pair.make( cls, propertyName );
		Getter value = GETTER_CACHE.get( key );
		if ( value == null ) {
			value = getAccessStrategy( cls, serviceRegistry, accessorType ).buildPropertyAccess( cls, propertyName ).getGetter();
			// It's ok if two getters are generated concurrently
			GETTER_CACHE.put( key, value );
		}

		return value;
	}
