	public CacheExpressionRootObject(
			Collection<? extends Cache> caches, Method method, Object[] args, Object target, Class<?> targetClass) {

		Assert.notNull(method, "Method is required");
		Assert.notNull(targetClass, "targetClass is required");
		this.method = method;
		this.target = target;
		this.targetClass = targetClass;
		this.args = args;
		this.caches = caches;
	}
