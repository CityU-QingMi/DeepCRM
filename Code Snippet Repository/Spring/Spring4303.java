	public Type getGenericParameterType() {
		Type paramType = this.genericParameterType;
		if (paramType == null) {
			if (this.parameterIndex < 0) {
				Method method = getMethod();
				paramType = (method != null ? method.getGenericReturnType() : void.class);
			}
			else {
				paramType = this.executable.getGenericParameterTypes()[this.parameterIndex];
			}
			this.genericParameterType = paramType;
		}
		return paramType;
	}
