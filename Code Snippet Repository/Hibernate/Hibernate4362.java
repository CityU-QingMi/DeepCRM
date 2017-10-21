		@SuppressWarnings({ "" })
		public Long convert(Object value) {
			if ( value == null ) {
				return null;
			}
			if ( Number.class.isInstance( value ) ) {
				return Long.valueOf( ( (Number) value ).longValue() );
			}
			else if ( String.class.isInstance( value ) ) {
				return Long.valueOf( ( (String) value ) );
			}
			throw unknownConversion( value, Long.class );
		}
