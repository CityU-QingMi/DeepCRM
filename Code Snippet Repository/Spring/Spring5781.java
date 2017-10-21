	public ReflectiveMethodExecutor(Method method) {
		this.method = method;
		if (method.isVarArgs()) {
			Class<?>[] paramTypes = method.getParameterTypes();
			this.varargsPosition = paramTypes.length - 1;
		}
		else {
			this.varargsPosition = null;
		}
	}
