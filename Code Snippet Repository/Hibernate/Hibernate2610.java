	public void initialize() throws SemanticException {
		final Node fixture = getFixtureOperand();
		if ( fixture == null ) {
			throw new SemanticException( "fixture operand of a between operator was null" );
		}

		final Node low = getLowOperand();
		if ( low == null ) {
			throw new SemanticException( "low operand of a between operator was null" );
		}

		final Node high = getHighOperand();
		if ( high == null ) {
			throw new SemanticException( "high operand of a between operator was null" );
		}

		Type expectedType = null;
		if ( fixture instanceof SqlNode ) {
			expectedType = ( (SqlNode) fixture ).getDataType();
		}
		if ( expectedType == null && low instanceof SqlNode ) {
			expectedType = ( (SqlNode) low ).getDataType();
		}
		if ( expectedType == null && high instanceof SqlNode ) {
			expectedType = ( (SqlNode) high ).getDataType();
		}

		if ( fixture instanceof ExpectedTypeAwareNode ) {
			( (ExpectedTypeAwareNode) fixture ).setExpectedType( expectedType );
		}
		if ( low instanceof ExpectedTypeAwareNode ) {
			( (ExpectedTypeAwareNode) low ).setExpectedType( expectedType );
		}
		if ( high instanceof ExpectedTypeAwareNode ) {
			( (ExpectedTypeAwareNode) high ).setExpectedType( expectedType );
		}
	}
