	public MappedSuperclassTypeImpl(
			Class<X> javaType,
			MappedSuperclass mappedSuperclass,
			AbstractIdentifiableType<? super X> superType) {
		super(
				javaType,
				javaType.getName(),
				superType,
				mappedSuperclass.getDeclaredIdentifierMapper() != null || ( superType != null && superType.hasIdClass() ),
				mappedSuperclass.hasIdentifierProperty(),
				mappedSuperclass.isVersioned()
		);
	}
