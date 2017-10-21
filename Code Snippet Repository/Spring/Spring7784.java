	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		SessionFactory sf = getSessionFactory();
		Assert.state(sf != null, "No SessionFactory set");

		if (!TransactionSynchronizationManager.hasResource(sf)) {
			// New Session to be bound for the current method's scope...
			Session session = openSession(sf);
			try {
				TransactionSynchronizationManager.bindResource(sf, new SessionHolder(session));
				return invocation.proceed();
			}
			finally {
				SessionFactoryUtils.closeSession(session);
				TransactionSynchronizationManager.unbindResource(sf);
			}
		}
		else {
			// Pre-bound Session found -> simply proceed.
			return invocation.proceed();
		}
	}
