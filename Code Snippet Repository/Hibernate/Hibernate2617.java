	protected Type extractDataType(Node operand) {
		Type type = null;
		if ( operand instanceof SqlNode ) {
			type = ( (SqlNode) operand ).getDataType();
		}
		if ( type == null && operand instanceof ExpectedTypeAwareNode ) {
			type = ( (ExpectedTypeAwareNode) operand ).getExpectedType();
		}
		return type;
	}
