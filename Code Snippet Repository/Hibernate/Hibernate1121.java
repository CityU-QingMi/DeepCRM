	@Override
	public void attributeInitialized(String name) {
		if ( !isLazyAttribute( name ) ) {
			return;
		}
		if ( initializedLazyFields == null ) {
			initializedLazyFields = new HashSet<String>();
		}
		initializedLazyFields.add( name );
	}
