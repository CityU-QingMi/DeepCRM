	@Override
	public final boolean unsetSession(SharedSessionContractImplementor currentSession) {
		prepareForPossibleLoadingOutsideTransaction();
		if ( currentSession == this.session ) {
			if ( !isTempSession ) {
				this.session = null;
			}
			return true;
		}
		else {
			if ( this.session != null ) {
				LOG.logCannotUnsetUnexpectedSessionInCollection( generateUnexpectedSessionStateMessage( currentSession ) );
			}
			return false;
		}
	}
