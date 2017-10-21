	@Override
	public void initialize() throws SemanticException {
		final Node lhs = getLeftHandOperand();
		if ( lhs == null ) {
			throw new SemanticException( "left-hand operand of a binary operator was null" );
		}

		final Node rhs = getRightHandOperand();
		if ( rhs == null ) {
			throw new SemanticException( "right-hand operand of a binary operator was null" );
		}

		Type lhsType = extractDataType( lhs );
		Type rhsType = extractDataType( rhs );

		if ( lhsType == null ) {
			lhsType = rhsType;
		}
		if ( rhsType == null ) {
			rhsType = lhsType;
		}

		if ( ExpectedTypeAwareNode.class.isAssignableFrom( lhs.getClass() ) ) {
			( (ExpectedTypeAwareNode) lhs ).setExpectedType( rhsType );
		}
		if ( ExpectedTypeAwareNode.class.isAssignableFrom( rhs.getClass() ) ) {
			( (ExpectedTypeAwareNode) rhs ).setExpectedType( lhsType );
		}

		mutateRowValueConstructorSyntaxesIfNecessary( lhsType, rhsType );
	}
