	@SuppressWarnings("")
	public static <X> PrimitiveWrapperDescriptor<X> getDescriptorByPrimitiveType(Class<X> primitiveClazz) {
		if ( ! primitiveClazz.isPrimitive() ) {
			throw new IllegalArgumentException( "Given class is not a primitive type : " + primitiveClazz.getName() );
		}

		if ( boolean.class == primitiveClazz ) {
			return (PrimitiveWrapperDescriptor<X>) BooleanDescriptor.INSTANCE;
		}

		if ( char.class == primitiveClazz ) {
			return (PrimitiveWrapperDescriptor<X>) CharacterDescriptor.INSTANCE;
		}

		if ( byte.class == primitiveClazz ) {
			return (PrimitiveWrapperDescriptor<X>) ByteDescriptor.INSTANCE;
		}

		if ( short.class == primitiveClazz ) {
			return (PrimitiveWrapperDescriptor<X>) ShortDescriptor.INSTANCE;
		}

		if ( int.class == primitiveClazz ) {
			return (PrimitiveWrapperDescriptor<X>) IntegerDescriptor.INSTANCE;
		}

		if ( long.class == primitiveClazz ) {
			return (PrimitiveWrapperDescriptor<X>) LongDescriptor.INSTANCE;
		}

		if ( float.class == primitiveClazz ) {
			return (PrimitiveWrapperDescriptor<X>) FloatDescriptor.INSTANCE;
		}

		if ( double.class == primitiveClazz ) {
			return (PrimitiveWrapperDescriptor<X>) DoubleDescriptor.INSTANCE;
		}

		if ( void.class == primitiveClazz ) {
			throw new IllegalArgumentException( "void, as primitive type, has no wrapper equivalent" );
		}

		throw new IllegalArgumentException( "Unrecognized primitive type class : " + primitiveClazz.getName() );
	}
