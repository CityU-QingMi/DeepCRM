	private static Type extractDataType(Node operand) {
		if ( operand instanceof SqlNode ) {
			return ( (SqlNode) operand ).getDataType();
		}

		if ( operand instanceof ExpectedTypeAwareNode ) {
			return ( (ExpectedTypeAwareNode) operand ).getExpectedType();
		}

		return null;
	}
