	@SuppressWarnings("")
	public static <X> PrimitiveWrapperDescriptor<X> getDescriptorByWrapperType(Class<X> wrapperClass) {
		if ( wrapperClass.isPrimitive() ) {
			throw new IllegalArgumentException( "Given class is a primitive type : " + wrapperClass.getName() );
		}

		if ( Boolean.class.equals( wrapperClass ) ) {
			return (PrimitiveWrapperDescriptor<X>) BooleanDescriptor.INSTANCE;
		}

		if ( Character.class == wrapperClass ) {
			return (PrimitiveWrapperDescriptor<X>) CharacterDescriptor.INSTANCE;
		}

		if ( Byte.class == wrapperClass ) {
			return (PrimitiveWrapperDescriptor<X>) ByteDescriptor.INSTANCE;
		}

		if ( Short.class == wrapperClass ) {
			return (PrimitiveWrapperDescriptor<X>) ShortDescriptor.INSTANCE;
		}

		if ( Integer.class == wrapperClass ) {
			return (PrimitiveWrapperDescriptor<X>) IntegerDescriptor.INSTANCE;
		}

		if ( Long.class == wrapperClass ) {
			return (PrimitiveWrapperDescriptor<X>) LongDescriptor.INSTANCE;
		}

		if ( Float.class == wrapperClass ) {
			return (PrimitiveWrapperDescriptor<X>) FloatDescriptor.INSTANCE;
		}

		if ( Double.class == wrapperClass ) {
			return (PrimitiveWrapperDescriptor<X>) DoubleDescriptor.INSTANCE;
		}

		// most likely void.class, which we can't really handle here
		throw new IllegalArgumentException( "Unrecognized wrapper type class : " + wrapperClass.getName() );
	}
