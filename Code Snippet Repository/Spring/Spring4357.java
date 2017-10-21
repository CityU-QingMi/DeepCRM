	public boolean hasUnresolvableGenerics() {
		if (this == NONE) {
			return false;
		}
		ResolvableType[] generics = getGenerics();
		for (ResolvableType generic : generics) {
			if (generic.isUnresolvableTypeVariable() || generic.isWildcardWithoutBounds()) {
				return true;
			}
		}
		Class<?> resolved = resolve();
		if (resolved != null) {
			for (Type genericInterface : resolved.getGenericInterfaces()) {
				if (genericInterface instanceof Class) {
					if (forClass((Class<?>) genericInterface).hasGenerics()) {
						return true;
					}
				}
			}
			return getSuperType().hasUnresolvableGenerics();
		}
		return false;
	}
