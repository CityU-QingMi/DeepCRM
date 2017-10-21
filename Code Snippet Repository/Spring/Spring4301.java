	public MethodParameter nested() {
		MethodParameter nestedParam = this.nestedMethodParameter;
		if (nestedParam != null) {
			return nestedParam;
		}
		nestedParam = clone();
		nestedParam.nestingLevel = this.nestingLevel + 1;
		this.nestedMethodParameter = nestedParam;
		return nestedParam;
	}
