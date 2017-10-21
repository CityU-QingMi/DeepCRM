	public String extractDetails(QuerySpace space) {
		if ( EntityQuerySpace.class.isInstance( space ) ) {
			final EntityQuerySpace entityQuerySpace = (EntityQuerySpace) space;
			return String.format(
					"%s(uid=%s, entity=%s)",
					entityQuerySpace.getClass().getSimpleName(),
					entityQuerySpace.getUid(),
					entityQuerySpace.getEntityPersister().getEntityName()
			);
		}
		else if ( CompositeQuerySpace.class.isInstance( space ) ) {
			final CompositeQuerySpace compositeQuerySpace = (CompositeQuerySpace) space;
			return String.format(
					"%s(uid=%s)",
					compositeQuerySpace.getClass().getSimpleName(),
					compositeQuerySpace.getUid()
			);
		}
		else if ( CollectionQuerySpace.class.isInstance( space ) ) {
			final CollectionQuerySpace collectionQuerySpace = (CollectionQuerySpace) space;
			return String.format(
					"%s(uid=%s, collection=%s)",
					collectionQuerySpace.getClass().getSimpleName(),
					collectionQuerySpace.getUid(),
					collectionQuerySpace.getCollectionPersister().getRole()
			);
		}
		return space.toString();
	}
