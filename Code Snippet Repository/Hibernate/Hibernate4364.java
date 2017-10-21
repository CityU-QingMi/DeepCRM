		@SuppressWarnings({ "" })
		public Double convert(Object value) {
			if ( value == null ) {
				return null;
			}
			if ( Number.class.isInstance( value ) ) {
				return Double.valueOf( ( (Number) value ).doubleValue() );
			}
			else if ( String.class.isInstance( value ) ) {
				return Double.valueOf( ( (String) value ) );
			}
			throw unknownConversion( value, Double.class );
		}
