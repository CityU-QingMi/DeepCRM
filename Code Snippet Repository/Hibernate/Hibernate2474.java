	protected Serializable getUpdateId(
			Object entity,
			EntityPersister persister,
			Serializable requestedId,
			SessionImplementor session) {
		// use the id assigned to the instance
		Serializable id = persister.getIdentifier( entity, session );
		if ( id == null ) {
			// assume this is a newly instantiated transient object
			// which should be saved rather than updated
			throw new TransientObjectException(
					"The given object has a null identifier: " +
							persister.getEntityName()
			);
		}
		else {
			return id;
		}

	}
