	@Override
	public Object instantiate(EntityPersister persister, Serializable id) throws HibernateException {
		checkOpenOrWaitingForAutoClose();
		checkTransactionSynchStatus();
		Object result = getInterceptor().instantiate(
				persister.getEntityName(),
				persister.getEntityMetamodel().getEntityMode(),
				id
		);
		if ( result == null ) {
			result = persister.instantiate( id, this );
		}
		delayedAfterCompletion();
		return result;
	}
