	@Nullable
	protected Object refreshAndRetry(MethodInvocation invocation) throws Throwable {
		try {
			refreshHome();
		}
		catch (NamingException ex) {
			throw new RemoteLookupFailureException("Failed to locate remote EJB [" + getJndiName() + "]", ex);
		}
		return doInvoke(invocation);
	}
