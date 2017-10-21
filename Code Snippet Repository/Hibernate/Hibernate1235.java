	private ParameterizedType extractAttributeConverterParameterizedType(Type base) {
		if ( base != null ) {
			Class clazz = extractClass( base );
			List<Type> types = new ArrayList<Type>();
			types.add( clazz.getGenericSuperclass() );
			types.addAll( Arrays.asList( clazz.getGenericInterfaces() ) );
			for ( Type type : types ) {
				type = resolveType( type, base );
				if ( ParameterizedType.class.isInstance( type ) ) {
					final ParameterizedType parameterizedType = (ParameterizedType) type;
					if ( AttributeConverter.class.equals( parameterizedType.getRawType() ) ) {
						return parameterizedType;
					}
				}
				ParameterizedType parameterizedType = extractAttributeConverterParameterizedType( type );
				if ( parameterizedType != null ) {
					return parameterizedType;
				}
			}
		}
		return null;
	}
