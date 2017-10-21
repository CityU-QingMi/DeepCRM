	private static Type getComponentType(Type actualType) {
		if ( actualType instanceof Class ) {
			Class<?> clazz = (Class<?>) actualType;
			if ( clazz.isArray() ) {
				return clazz.getComponentType();
			}
			else {
				fail( "Unexpected component type" );
			}
		}

		if ( actualType instanceof GenericArrayType ) {
			return ( (GenericArrayType) actualType ).getGenericComponentType();
		}
		else {
			fail( "Unexpected component type" );
			return null;
		}
	}
