	public SingularAttributeImpl(
			String name,
			Class<Y> javaType,
			AbstractManagedType<X> declaringType,
			Member member,
			boolean isIdentifier,
			boolean isVersion,
			boolean isOptional,
			Type<Y> attributeType,
			PersistentAttributeType persistentAttributeType) {
		super( name, javaType, declaringType, member, persistentAttributeType );
		this.isIdentifier = isIdentifier;
		this.isVersion = isVersion;
		this.isOptional = isOptional;
		this.attributeType = attributeType;
	}
