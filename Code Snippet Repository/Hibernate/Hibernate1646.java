	private boolean needsWrapping(Session session) {
		// try to make sure we don't wrap and already wrapped session
		if ( session != null ) {
			if ( Proxy.isProxyClass( session.getClass() ) ) {
				final InvocationHandler invocationHandler = Proxy.getInvocationHandler( session );
				if ( invocationHandler != null && TransactionProtectionWrapper.class.isInstance( invocationHandler ) ) {
					return false;
				}
			}
		}
		return true;
	}
