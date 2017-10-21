		public BigInteger convert(Object value) {
			if ( value == null ) {
				return null;
			}
			if ( Number.class.isInstance( value ) ) {
				return BigInteger.valueOf( ( (Number) value ).longValue() );
			}
			else if ( String.class.isInstance( value ) ) {
				return new BigInteger( (String) value );
			}
			throw unknownConversion( value, BigInteger.class );
		}
