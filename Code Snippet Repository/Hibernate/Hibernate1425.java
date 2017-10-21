	public SimpleValue make() {

		validate();
		LOG.debugf( "building SimpleValue for %s", propertyName );
		if ( table == null ) {
			table = columns[0].getTable();
		}
		simpleValue = new SimpleValue( buildingContext.getMetadataCollector(), table );
		if ( isVersion ) {
			simpleValue.makeVersion();
		}
		if ( isNationalized ) {
			simpleValue.makeNationalized();
		}
		if ( isLob ) {
			simpleValue.makeLob();
		}

		linkWithValue();

		boolean isInSecondPass = buildingContext.getMetadataCollector().isInSecondPass();
		if ( !isInSecondPass ) {
			//Defer this to the second pass
			buildingContext.getMetadataCollector().addSecondPass( new SetSimpleValueTypeSecondPass( this ) );
		}
		else {
			//We are already in second pass
			fillSimpleValue();
		}
		return simpleValue;
	}
