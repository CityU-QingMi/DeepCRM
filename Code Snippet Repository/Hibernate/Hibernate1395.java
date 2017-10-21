		private static Boolean interpretBoolean(Object value) {
			if ( value == null ) {
				return null;
			}

			if ( value instanceof Boolean ) {
				return (Boolean) value;
			}

			return Boolean.valueOf( value.toString() );
		}
