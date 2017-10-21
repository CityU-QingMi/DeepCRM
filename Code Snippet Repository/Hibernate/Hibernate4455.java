	private ManagedType<X> resolveManagedType(SingularAttribute<?, X> attribute) {
		if ( Attribute.PersistentAttributeType.BASIC == attribute.getPersistentAttributeType() ) {
			return null;
		}
		else if ( Attribute.PersistentAttributeType.EMBEDDED == attribute.getPersistentAttributeType() ) {
			return (EmbeddableType<X>) attribute.getType();
		}
		else {
			return (IdentifiableType<X>) attribute.getType();
		}
	}
