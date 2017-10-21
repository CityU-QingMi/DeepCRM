	private Object invokeOperation(Method method, Object[] args) throws JMException, IOException {
		Assert.state(this.serverToUse != null, "No MBeanServerConnection available");

		MethodCacheKey key = new MethodCacheKey(method.getName(), method.getParameterTypes());
		MBeanOperationInfo info = this.allowedOperations.get(key);
		if (info == null) {
			throw new InvalidInvocationException("Operation '" + method.getName() +
					"' is not exposed on the management interface");
		}

		String[] signature;
		synchronized (this.signatureCache) {
			signature = this.signatureCache.get(method);
			if (signature == null) {
				signature = JmxUtils.getMethodSignature(method);
				this.signatureCache.put(method, signature);
			}
		}

		return this.serverToUse.invoke(this.objectName, method.getName(), args, signature);
	}
