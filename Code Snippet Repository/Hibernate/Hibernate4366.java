		public BigDecimal convert(Object value) {
			if ( value == null ) {
				return null;
			}
			if ( BigInteger.class.isInstance( value ) ) {
				return new BigDecimal( (BigInteger) value );
			}
			else if ( Number.class.isInstance( value ) ) {
				return BigDecimal.valueOf( ( (Number) value ).doubleValue() );
			}
			else if ( String.class.isInstance( value ) ) {
				return new BigDecimal( (String) value );
			}
			throw unknownConversion( value, BigDecimal.class );
		}
