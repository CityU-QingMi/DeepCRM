		@SuppressWarnings({ "" })
		public Integer convert(Object value) {
			if ( value == null ) {
				return null;
			}
			if ( Number.class.isInstance( value ) ) {
				return Integer.valueOf( ( (Number) value ).intValue() );
			}
			else if ( String.class.isInstance( value ) ) {
				return Integer.valueOf( ( (String) value ) );
			}
			throw unknownConversion( value, Integer.class );
		}
