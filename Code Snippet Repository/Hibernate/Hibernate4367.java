		public Boolean convert(Object value) {
			if ( value == null ) {
				return null;
			}
			if ( Boolean.class.isInstance( value ) ) {
				return (Boolean) value;
			}
			if ( String.class.isInstance( value ) ) {
				return Boolean.getBoolean( (String) value );
			}
			throw unknownConversion( value, Boolean.class );
		}
