	@Override
	public final void setSession(SharedSessionContractImplementor s) throws HibernateException {
		if ( s != session ) {
			// check for s == null first, since it is least expensive
			if ( s == null ) {
				unsetSession();
			}
			else if ( isConnectedToSession() ) {
				//TODO: perhaps this should be some other RuntimeException...
				throw new HibernateException( "illegally attempted to associate a proxy with two open Sessions" );
			}
			else {
				// s != null
				session = s;
				if ( readOnlyBeforeAttachedToSession == null ) {
					// use the default read-only/modifiable setting
					final EntityPersister persister = s.getFactory().getEntityPersister( entityName );
					setReadOnly( s.getPersistenceContext().isDefaultReadOnly() || !persister.isMutable() );
				}
				else {
					// use the read-only/modifiable setting indicated during deserialization
					setReadOnly( readOnlyBeforeAttachedToSession );
					readOnlyBeforeAttachedToSession = null;
				}
			}
		}
	}
