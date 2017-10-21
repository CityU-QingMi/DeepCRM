		@SuppressWarnings({ "" })
		public Byte convert(Object value) {
			if ( value == null ) {
				return null;
			}
			if ( Number.class.isInstance( value ) ) {
				return Byte.valueOf( ( (Number) value ).byteValue() );
			}
			else if ( String.class.isInstance( value ) ) {
				return Byte.valueOf( ( (String) value ) );
			}
			throw unknownConversion( value, Byte.class );
		}
