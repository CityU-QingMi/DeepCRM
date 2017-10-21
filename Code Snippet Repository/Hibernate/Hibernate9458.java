	private double extractDoubleValue(Object value) {
		if ( value instanceof BigInteger ) {
			return ( ( BigInteger ) value ).doubleValue();
		}
		else if ( value instanceof BigDecimal ) {
			return ( ( BigDecimal ) value ).doubleValue();
		}
		else {
			return Double.valueOf( value.toString() ).doubleValue();
		}
	}
