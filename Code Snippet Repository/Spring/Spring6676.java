	@Nullable
	public Method getMostSpecificMethod() {
		if (this.mostSpecificMethod != null) {
			return this.mostSpecificMethod;
		}
		Method method = getMethod();
		if (method != null && AopUtils.isAopProxy(this.bean)) {
			Class<?> target = AopProxyUtils.ultimateTargetClass(this.bean);
			return AopUtils.getMostSpecificMethod(method, target);
		}
		else {
			return method;
		}
	}
