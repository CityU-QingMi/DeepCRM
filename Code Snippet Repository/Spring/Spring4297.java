	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MethodParameter)) {
			return false;
		}
		MethodParameter otherParam = (MethodParameter) other;
		return (this.parameterIndex == otherParam.parameterIndex && getExecutable().equals(otherParam.getExecutable()));
	}
