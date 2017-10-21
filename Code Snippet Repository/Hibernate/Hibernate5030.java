	public Serializable getIdOfOwnerOrNull(Serializable key, SharedSessionContractImplementor session) {
		Serializable ownerId = null;
		if ( foreignKeyPropertyName == null ) {
			ownerId = key;
		}
		else {
			Type keyType = getPersister( session ).getKeyType();
			EntityPersister ownerPersister = getPersister( session ).getOwnerEntityPersister();
			// TODO: Fix this so it will work for non-POJO entity mode
			Class ownerMappedClass = ownerPersister.getMappedClass();
			if ( ownerMappedClass.isAssignableFrom( keyType.getReturnedClass() ) &&
					keyType.getReturnedClass().isInstance( key ) ) {
				// the key is the owning entity itself, so get the ID from the key
				ownerId = ownerPersister.getIdentifier( key, session );
			}
			else {
				// TODO: check if key contains the owner ID
			}
		}
		return ownerId;
	}
