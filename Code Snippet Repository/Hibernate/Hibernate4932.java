	private static Getter getGetter(Property mappingProperty) {
		if ( mappingProperty == null || !mappingProperty.getPersistentClass().hasPojoRepresentation() ) {
			return null;
		}

		final PropertyAccessStrategyResolver propertyAccessStrategyResolver =
				mappingProperty.getPersistentClass().getServiceRegistry().getService( PropertyAccessStrategyResolver.class );

		final PropertyAccessStrategy propertyAccessStrategy = propertyAccessStrategyResolver.resolvePropertyAccessStrategy(
				mappingProperty.getClass(),
				mappingProperty.getPropertyAccessorName(),
				EntityMode.POJO
		);

		final PropertyAccess propertyAccess = propertyAccessStrategy.buildPropertyAccess(
				mappingProperty.getPersistentClass().getMappedClass(),
				mappingProperty.getName()
		);

		return propertyAccess.getGetter();
	}
