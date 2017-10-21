	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MethodSource that = (MethodSource) o;
		return Objects.equals(this.className, that.className) && Objects.equals(this.methodName, that.methodName)
				&& Objects.equals(this.methodParameterTypes, that.methodParameterTypes);
	}
