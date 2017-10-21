	private void errorIfReadOnlySettingNotAvailable() {
		if ( session == null ) {
			throw new TransientObjectException(
					"Proxy is detached (i.e, session is null). The read-only/modifiable setting is only accessible when the proxy is associated with an open session."
			);
		}
		if ( session.isClosed() ) {
			throw new SessionException(
					"Session is closed. The read-only/modifiable setting is only accessible when the proxy is associated with an open session."
			);
		}
	}
