	@SuppressWarnings("")
	public EntityTypeImpl(Class javaType, AbstractIdentifiableType<? super X> superType, PersistentClass persistentClass) {
		super(
				javaType,
				persistentClass.getEntityName(),
				superType,
				persistentClass.getDeclaredIdentifierMapper() != null || ( superType != null && superType.hasIdClass() ),
				persistentClass.hasIdentifierProperty(),
				persistentClass.isVersioned()
		);
		this.jpaEntityName = persistentClass.getJpaEntityName();
	}
