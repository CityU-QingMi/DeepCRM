	@Override
	public <X> Character[] wrap(X value, WrapperOptions options) {
		if ( value == null ) {
			return null;
		}
		if ( Character[].class.isInstance( value ) ) {
			return (Character[]) value;
		}
		if ( String.class.isInstance( value ) ) {
			return wrapChars( ( (String) value ).toCharArray() );
		}
		if ( Clob.class.isInstance( value ) ) {
			return wrapChars( DataHelper.extractString( ( (Clob) value ) ).toCharArray() );
		}
		if ( Reader.class.isInstance( value ) ) {
			return wrapChars( DataHelper.extractString( (Reader) value ).toCharArray() );
		}
		throw unknownWrap( value.getClass() );
	}
