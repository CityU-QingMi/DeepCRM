	@Override
	public void registerNaturalIdUniqueKeyBinder(String entityName, NaturalIdUniqueKeyBinder ukBinder) {
		if ( naturalIdUniqueKeyBinderMap == null ) {
			naturalIdUniqueKeyBinderMap = new HashMap<String, NaturalIdUniqueKeyBinder>();
		}
		final NaturalIdUniqueKeyBinder previous = naturalIdUniqueKeyBinderMap.put( entityName, ukBinder );
		if ( previous != null ) {
			throw new AssertionFailure( "Previous NaturalIdUniqueKeyBinder already registered for entity name : " + entityName );
		}
	}
