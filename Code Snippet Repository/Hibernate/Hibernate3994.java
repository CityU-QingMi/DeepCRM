	private <Y> void checkTypeForSingleAttribute(
			String attributeType,
			SingularAttribute<?,?> attribute,
			String name,
			Class<Y> javaType) {
		if ( attribute == null || ( javaType != null && !attribute.getBindableJavaType().equals( javaType ) ) ) {
			if ( isPrimitiveVariant( attribute, javaType ) ) {
				return;
			}
			throw new IllegalArgumentException(
					attributeType + " named " + name
					+ ( javaType != null ? " and of type " + javaType.getName() : "" )
					+ " is not present"
			);
		}
	}
