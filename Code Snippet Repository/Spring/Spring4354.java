	public ResolvableType getSuperType() {
		Class<?> resolved = resolve();
		if (resolved == null || resolved.getGenericSuperclass() == null) {
			return NONE;
		}
		if (this.superType == null) {
			this.superType = forType(SerializableTypeWrapper.forGenericSuperclass(resolved), asVariableResolver());
		}
		return this.superType;
	}
