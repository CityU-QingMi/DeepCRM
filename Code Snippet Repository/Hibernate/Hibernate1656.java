	protected void addPropertyTypedValue(Object value, Type type, List<TypedValue> list) {
		if ( value != null ) {
			if ( value instanceof String ) {
				String string = (String) value;
				if ( isIgnoreCaseEnabled ) {
					string = string.toLowerCase(Locale.ROOT);
				}
				if ( isLikeEnabled ) {
					string = matchMode.toMatchString( string );
				}
				value = string;
			}
			list.add( new TypedValue( type, value ) );
		}
	}
