		private Session getCloseSuppressingSessionProxy(Session target) {
			List<Class<?>> classes = new ArrayList<>(3);
			classes.add(SessionProxy.class);
			if (target instanceof QueueSession) {
				classes.add(QueueSession.class);
			}
			if (target instanceof TopicSession) {
				classes.add(TopicSession.class);
			}
			return (Session) Proxy.newProxyInstance(
					SessionProxy.class.getClassLoader(),
					classes.toArray(new Class<?>[classes.size()]),
					new CloseSuppressingSessionInvocationHandler(target));
		}
