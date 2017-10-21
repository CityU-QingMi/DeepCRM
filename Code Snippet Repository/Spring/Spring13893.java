	private void unregisterTyrusEndpoint(TyrusWebSocketEngine engine, @Nullable Object tyrusEndpoint) {
		if (tyrusEndpoint != null) {
			try {
				unregister(engine, tyrusEndpoint);
			}
			catch (Throwable ex) {
				// ignore
			}
		}
	}
