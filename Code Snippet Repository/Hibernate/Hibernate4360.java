		@SuppressWarnings({ "" })
		public Short convert(Object value) {
			if ( value == null ) {
				return null;
			}
			if ( Number.class.isInstance( value ) ) {
				return Short.valueOf( ( (Number) value ).shortValue() );
			}
			else if ( String.class.isInstance( value ) ) {
				return Short.valueOf( ( (String) value ) );
			}
			throw unknownConversion( value, Short.class );
		}
