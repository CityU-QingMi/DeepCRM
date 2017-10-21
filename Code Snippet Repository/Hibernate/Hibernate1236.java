	private static ParameterizedType resolveParameterizedType(final ParameterizedType parameterizedType, Type context) {
		Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

		final Type[] resolvedTypeArguments = new Type[actualTypeArguments.length];
		for ( int idx = 0; idx < actualTypeArguments.length; idx++ ) {
			resolvedTypeArguments[idx] = resolveType( actualTypeArguments[idx], context );
		}
		return new ParameterizedType() {

			@Override
			public Type[] getActualTypeArguments() {
				return resolvedTypeArguments;
			}

			@Override
			public Type getRawType() {
				return parameterizedType.getRawType();
			}

			@Override
			public Type getOwnerType() {
				return parameterizedType.getOwnerType();
			}

		};
	}
