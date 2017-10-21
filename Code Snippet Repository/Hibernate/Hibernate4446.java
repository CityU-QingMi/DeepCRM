	private EntityType locateNearestSubclassEntity(MappedSuperclassType mappedSuperclassType, EntityType entityTypeTop) {
		EntityType entityTypeNearestDeclaringType = entityTypeTop;
		IdentifiableType superType = entityTypeNearestDeclaringType.getSupertype();
		while ( superType != mappedSuperclassType ) {
			if ( superType == null ) {
				throw new IllegalStateException(
						String.format(
								"Cannot determine nearest EntityType extending mapped superclass [%s] starting from [%s]; a supertype of [%s] is null",
								mappedSuperclassType.getJavaType().getName(),
								entityTypeTop.getJavaType().getName(),
								entityTypeTop.getJavaType().getName()
						)
				);
			}
			if ( superType.getPersistenceType() == Type.PersistenceType.ENTITY ) {
				entityTypeNearestDeclaringType = (EntityType) superType;
			}
			superType = superType.getSupertype();
		}
		return entityTypeNearestDeclaringType;
	}
