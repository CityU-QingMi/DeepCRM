		@SuppressWarnings({ "" })
		public Float convert(Object value) {
			if ( value == null ) {
				return null;
			}
			if ( Number.class.isInstance( value ) ) {
				return Float.valueOf( ( (Number) value ).floatValue() );
			}
			else if ( String.class.isInstance( value ) ) {
				return Float.valueOf( ( (String) value ) );
			}
			throw unknownConversion( value, Float.class );
		}
