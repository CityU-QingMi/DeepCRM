	public static ParameterizedType getSignatureType(Member member) {
		final java.lang.reflect.Type type;
		if ( Field.class.isInstance( member ) ) {
			type = ( (Field) member ).getGenericType();
		}
		else if ( Method.class.isInstance( member ) ) {
			type = ( (Method) member ).getGenericReturnType();
		}
		else {
			type = ( (MapMember) member ).getType();
		}
		//this is a raw type
		if ( type instanceof Class ) {
			return null;
		}
		return (ParameterizedType) type;
	}
