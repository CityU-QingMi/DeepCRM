	@Nullable
	public String getScalarOutParameterName() {
		if (isFunction()) {
			return getFunctionReturnName();
		}
		else {
			if (this.outParameterNames.size() > 1) {
				logger.warn("Accessing single output value when procedure has more than one output parameter");
			}
			return (this.outParameterNames.size() > 0 ? this.outParameterNames.get(0) : null);
		}
	}
