	@Override
	@Nullable
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// Lazily connect to MBeanServer if necessary.
		synchronized (this.preparationMonitor) {
			if (!isPrepared()) {
				prepare();
			}
		}
		try {
			return doInvoke(invocation);
		}
		catch (MBeanConnectFailureException ex) {
			return handleConnectFailure(invocation, ex);
		}
		catch (IOException ex) {
			return handleConnectFailure(invocation, ex);
		}
	}
