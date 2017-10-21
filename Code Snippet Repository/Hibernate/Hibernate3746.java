	public JoinImpl(
			QuerySpace leftHandSide,
			String lhsPropertyName,
			QuerySpace rightHandSide,
			String[] rhsColumnNames,
			Type joinedPropertyType,
			boolean rightHandSideRequired) {
		this.leftHandSide = leftHandSide;
		this.lhsPropertyName = lhsPropertyName;
		this.rightHandSide = rightHandSide;
		this.rhsColumnNames = rhsColumnNames;
		this.rightHandSideRequired = rightHandSideRequired;
		this.joinedPropertyType = joinedPropertyType;
		if ( StringHelper.isEmpty( lhsPropertyName ) ) {
			throw new IllegalArgumentException( "Incoming 'lhsPropertyName' parameter was empty" );
		}
	}
