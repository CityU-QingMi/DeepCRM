	@Override
	protected Serializable saveWithRequestedId(
			Object entity,
			Serializable requestedId,
			String entityName,
			Object anything,
			EventSource source) {
		callbackRegistry.preCreate( entity );
		return super.saveWithRequestedId( entity, requestedId, entityName, anything, source );
	}
