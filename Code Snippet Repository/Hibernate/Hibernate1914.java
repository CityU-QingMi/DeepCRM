		@Override
		public Boolean convert(Object value) {
			if ( value == null ) {
				throw new IllegalArgumentException( "Null value passed to convert" );
			}

			return Boolean.class.isInstance( value )
					? Boolean.class.cast( value )
					: Boolean.parseBoolean( value.toString() );
		}
