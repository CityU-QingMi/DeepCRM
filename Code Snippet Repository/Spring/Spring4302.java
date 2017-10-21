	public Class<?> getParameterType() {
		Class<?> paramType = this.parameterType;
		if (paramType == null) {
			if (this.parameterIndex < 0) {
				Method method = getMethod();
				paramType = (method != null ? method.getReturnType() : void.class);
			}
			else {
				paramType = this.executable.getParameterTypes()[this.parameterIndex];
			}
			this.parameterType = paramType;
		}
		return paramType;
	}
