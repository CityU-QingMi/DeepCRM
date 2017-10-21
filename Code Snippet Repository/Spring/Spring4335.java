	@Nullable
	private Class<?> resolveClass() {
		if (this.type == EmptyType.INSTANCE) {
			return null;
		}
		if (this.type instanceof Class) {
			return (Class<?>) this.type;
		}
		if (this.type instanceof GenericArrayType) {
			Class<?> resolvedComponent = getComponentType().resolve();
			return (resolvedComponent != null ? Array.newInstance(resolvedComponent, 0).getClass() : null);
		}
		return resolveType().resolve();
	}
