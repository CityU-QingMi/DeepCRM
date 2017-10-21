	private PluralAttributeImpl(Builder<X,C,E,?> builder) {
		super(
				builder.property.getName(),
				builder.collectionClass,
				builder.type,
				builder.member,
				builder.persistentAttributeType
		);
		this.elementType = builder.attributeType;
	}
