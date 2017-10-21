	@Override
	public boolean supports(Type genericType) {
		if (genericType instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) genericType;
			if (JAXBElement.class == parameterizedType.getRawType() &&
					parameterizedType.getActualTypeArguments().length == 1) {
				Type typeArgument = parameterizedType.getActualTypeArguments()[0];
				if (typeArgument instanceof Class) {
					Class<?> classArgument = (Class<?>) typeArgument;
					return (((classArgument.isArray() && Byte.TYPE == classArgument.getComponentType())) ||
							isPrimitiveWrapper(classArgument) || isStandardClass(classArgument) ||
							supportsInternal(classArgument, false));
				}
				else if (typeArgument instanceof GenericArrayType) {
					GenericArrayType arrayType = (GenericArrayType) typeArgument;
					return (Byte.TYPE == arrayType.getGenericComponentType());
				}
			}
		}
		else if (genericType instanceof Class) {
			Class<?> clazz = (Class<?>) genericType;
			return supportsInternal(clazz, this.checkForXmlRootElement);
		}
		return false;
	}
