	@Override
	public final boolean setCurrentSession(SharedSessionContractImplementor session) throws HibernateException {
		if ( session == this.session ) {
			return false;
		}
		else {
			if ( this.session != null ) {
				final String msg = generateUnexpectedSessionStateMessage( session );
				if ( isConnectedToSession() ) {
					throw new HibernateException(
							"Illegal attempt to associate a collection with two open sessions. " + msg
					);
				}
				else {
					LOG.logUnexpectedSessionInCollectionNotConnected( msg );
					this.session = session;
					return true;
				}
			}
			else {
				this.session = session;
				return true;
			}
		}
	}
