	public AbstractIdentifiableType(
			Class<X> javaType,
			String typeName,
			AbstractIdentifiableType<? super X> superType,
			boolean hasIdClass,
			boolean hasIdentifierProperty,
			boolean versioned) {
		super( javaType, typeName, superType );
		this.hasIdClass = hasIdClass;
		this.hasIdentifierProperty = hasIdentifierProperty;
		this.isVersioned = versioned;
	}
