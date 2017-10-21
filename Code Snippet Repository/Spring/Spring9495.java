	@Nullable
	protected RemoteAccessException convertHttpInvokerAccessException(Throwable ex) {
		if (ex instanceof ConnectException) {
			return new RemoteConnectFailureException(
					"Could not connect to HTTP invoker remote service at [" + getServiceUrl() + "]", ex);
		}

		if (ex instanceof ClassNotFoundException || ex instanceof NoClassDefFoundError ||
				ex instanceof InvalidClassException) {
			return new RemoteAccessException(
					"Could not deserialize result from HTTP invoker remote service [" + getServiceUrl() + "]", ex);
		}

		if (ex instanceof Exception) {
			return new RemoteAccessException(
					"Could not access HTTP invoker remote service at [" + getServiceUrl() + "]", ex);
		}

		// For any other Throwable, e.g. OutOfMemoryError: let it get propagated as-is.
		return null;
	}
