	public EntityUniqueKey(
			final String entityName,
			final String uniqueKeyName,
			final Object semiResolvedKey,
			final Type keyType,
			final EntityMode entityMode,
			final SessionFactoryImplementor factory) {
		this.uniqueKeyName = uniqueKeyName;
		this.entityName = entityName;
		this.key = semiResolvedKey;
		this.keyType = keyType.getSemiResolvedType( factory );
		this.entityMode = entityMode;
		this.hashCode = generateHashCode( factory );
	}
