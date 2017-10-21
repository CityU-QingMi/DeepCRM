	@SuppressWarnings("")
	public static boolean isCollectionProperty(String name) {
		final String key = name.toLowerCase(Locale.ROOT);
		// CollectionPropertyMapping processes everything except 'index'.
		if ( COLLECTION_INDEX_LOWER.equals( key ) ) {
			return false;
		}
		else {
			return HQL_COLLECTION_PROPERTIES.containsKey( key );
		}
	}
