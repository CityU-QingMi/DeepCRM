	private CollectionKey(
			String role,
			Serializable key,
			Type keyType,
			EntityMode entityMode,
			SessionFactoryImplementor factory) {
		this.role = role;
		this.key = key;
		this.keyType = keyType;
		this.entityMode = entityMode;
		this.factory = factory;
		//cache the hash-code
		this.hashCode = generateHashCode();
	}
