	@Override
	public boolean canWrite(@Nullable Type type, @Nullable Class<?> clazz, @Nullable MediaType mediaType) {
		if (!(type instanceof ParameterizedType)) {
			return (type instanceof Class && ResourceRegion.class.isAssignableFrom((Class<?>) type));
		}

		ParameterizedType parameterizedType = (ParameterizedType) type;
		if (!(parameterizedType.getRawType() instanceof Class)) {
			return false;
		}
		Class<?> rawType = (Class<?>) parameterizedType.getRawType();
		if (!(Collection.class.isAssignableFrom(rawType))) {
			return false;
		}
		if (parameterizedType.getActualTypeArguments().length != 1) {
			return false;
		}
		Type typeArgument = parameterizedType.getActualTypeArguments()[0];
		if (!(typeArgument instanceof Class)) {
			return false;
		}

		Class<?> typeArgumentClass = (Class<?>) typeArgument;
		return typeArgumentClass.isAssignableFrom(ResourceRegion.class);
	}
