	private Object[] determinePreviousNaturalIdValues(
			EntityPersister persister,
			Object[] previousState,
			SharedSessionContractImplementor session,
			Serializable id) {
		if ( ! persister.hasNaturalIdentifier() ) {
			return null;
		}

		if ( previousState != null ) {
			return session.getPersistenceContext().getNaturalIdHelper().extractNaturalIdValues( previousState, persister );
		}

		return session.getPersistenceContext().getNaturalIdSnapshot( id, persister );
	}
