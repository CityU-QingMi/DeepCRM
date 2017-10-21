	@Override
	public HydratedCompoundValueHandler getHydratedCompoundValueExtractor() {
		if ( hydratedCompoundValueHandler == null ) {
			hydratedCompoundValueHandler = new HydratedCompoundValueHandler() {
				@Override
				public Object extract(Object hydratedState) {
					return ( (Object[] ) hydratedState )[ attributeNumber() ];
				}

				@Override
				public void inject(Object hydratedState, Object value) {
					( (Object[] ) hydratedState )[ attributeNumber() ] = value;
				}
			};
		}
		return hydratedCompoundValueHandler;
	}
