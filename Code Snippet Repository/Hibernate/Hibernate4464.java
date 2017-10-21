	public static Class<? extends Number> determineResultType(Class<? extends Number>... types) {
		Class<? extends Number> result = Number.class;

		for ( Class<? extends Number> type : types ) {
			if ( Double.class.equals( type ) ) {
				result = Double.class;
			}
			else if ( Float.class.equals( type ) ) {
				result = Float.class;
			}
			else if ( BigDecimal.class.equals( type ) ) {
				result = BigDecimal.class;
			}
			else if ( BigInteger.class.equals( type ) ) {
				result = BigInteger.class;
			}
			else if ( Long.class.equals( type ) ) {
				result = Long.class;
			}
			else if ( isIntegralType( type ) ) {
				result = Integer.class;
			}
		}

		return result;
	}
