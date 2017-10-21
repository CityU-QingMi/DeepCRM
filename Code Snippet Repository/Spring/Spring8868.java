	public void addTransactionalMethod(String name, TransactionAttribute attr) {
		Assert.notNull(name, "Name must not be null");
		int lastDotIndex = name.lastIndexOf(".");
		if (lastDotIndex == -1) {
			throw new IllegalArgumentException("'" + name + "' is not a valid method name: format is FQN.methodName");
		}
		String className = name.substring(0, lastDotIndex);
		String methodName = name.substring(lastDotIndex + 1);
		Class<?> clazz = ClassUtils.resolveClassName(className, this.beanClassLoader);
		addTransactionalMethod(clazz, methodName, attr);
	}
