	public ResolvableType[] getGenerics() {
		if (this == NONE) {
			return EMPTY_TYPES_ARRAY;
		}
		if (this.generics == null) {
			if (this.type instanceof Class) {
				Class<?> typeClass = (Class<?>) this.type;
				this.generics = forTypes(SerializableTypeWrapper.forTypeParameters(typeClass), this.variableResolver);
			}
			else if (this.type instanceof ParameterizedType) {
				Type[] actualTypeArguments = ((ParameterizedType) this.type).getActualTypeArguments();
				ResolvableType[] generics = new ResolvableType[actualTypeArguments.length];
				for (int i = 0; i < actualTypeArguments.length; i++) {
					generics[i] = forType(actualTypeArguments[i], this.variableResolver);
				}
				this.generics = generics;
			}
			else {
				this.generics = resolveType().getGenerics();
			}
		}
		return this.generics;
	}
