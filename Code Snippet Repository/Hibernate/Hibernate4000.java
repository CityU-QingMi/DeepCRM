		private SingularAttributeMetadataImpl(
				Property propertyMapping,
				AbstractManagedType<X> ownerType,
				Member member,
				Attribute.PersistentAttributeType persistentAttributeType) {
			super( propertyMapping, ownerType, member, persistentAttributeType );
			valueContext = new ValueContext() {
				public Value getValue() {
					return getPropertyMapping().getValue();
				}

				public Class getBindableType() {
					return getAttributeMetadata().getJavaType();
				}

				public ValueClassification getValueClassification() {
					switch ( getPersistentAttributeType() ) {
						case EMBEDDED: {
							return ValueClassification.EMBEDDABLE;
						}
						case BASIC: {
							return ValueClassification.BASIC;
						}
						default: {
							return ValueClassification.ENTITY;
						}
					}
				}

				public AttributeMetadata getAttributeMetadata() {
					return SingularAttributeMetadataImpl.this;
				}
			};
		}
