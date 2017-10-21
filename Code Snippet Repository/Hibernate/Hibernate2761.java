	public static Map generateTokenNameCache(Class tokenTypeInterface) {
		final Field[] fields = tokenTypeInterface.getFields();
		Map cache = new HashMap( (int) ( fields.length * .75 ) + 1 );
		for ( final Field field : fields ) {
			if ( Modifier.isStatic( field.getModifiers() ) ) {
				try {
					cache.put( field.get( null ), field.getName() );
				}
				catch (Throwable ignore) {
				}
			}
		}
		return cache;
	}
