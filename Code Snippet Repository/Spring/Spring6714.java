	protected Connection getTransactionAwareConnectionProxy(Connection target) {
		List<Class<?>> classes = new ArrayList<>(3);
		classes.add(Connection.class);
		if (target instanceof QueueConnection) {
			classes.add(QueueConnection.class);
		}
		if (target instanceof TopicConnection) {
			classes.add(TopicConnection.class);
		}
		return (Connection) Proxy.newProxyInstance(
				Connection.class.getClassLoader(),
				classes.toArray(new Class<?>[classes.size()]),
				new TransactionAwareConnectionInvocationHandler(target));
	}
