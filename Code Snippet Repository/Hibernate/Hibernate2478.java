	protected Serializable getUpdateId(
			Object entity,
			EntityPersister persister,
			Serializable requestedId,
			SessionImplementor session) throws HibernateException {
		if ( requestedId == null ) {
			return super.getUpdateId( entity, persister, requestedId, session );
		}
		else {
			persister.setIdentifier( entity, requestedId, session );
			return requestedId;
		}
	}
