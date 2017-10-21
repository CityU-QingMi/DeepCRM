	@Override
	@SuppressWarnings("")
	protected void addSettings(Map settings) {
		super.addSettings( settings );

		applyStandardSettings( settings );

		settings.put( NODE_ID_PROP, LOCAL );
		settings.put( NODE_ID_FIELD, LOCAL );
		settings.put( REGION_FACTORY_DELEGATE, getRegionFactoryClass() );
	}
