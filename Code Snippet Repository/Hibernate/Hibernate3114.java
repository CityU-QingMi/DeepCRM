	@Override
	public Serializable getContextEntityIdentifier(Object object) {
		checkOpenOrWaitingForAutoClose();
		if ( object instanceof HibernateProxy ) {
			return getProxyIdentifier( object );
		}
		else {
			EntityEntry entry = persistenceContext.getEntry( object );
			return entry != null ? entry.getId() : null;
		}
	}
