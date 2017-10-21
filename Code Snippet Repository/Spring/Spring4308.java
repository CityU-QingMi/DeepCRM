	@Nullable
	public String getParameterName() {
		ParameterNameDiscoverer discoverer = this.parameterNameDiscoverer;
		if (discoverer != null) {
			String[] parameterNames = null;
			if (this.executable instanceof Method) {
				parameterNames = discoverer.getParameterNames((Method) this.executable);
			}
			else if (this.executable instanceof Constructor) {
				parameterNames = discoverer.getParameterNames((Constructor) this.executable);
			}
			if (parameterNames != null) {
				this.parameterName = parameterNames[this.parameterIndex];
			}
			this.parameterNameDiscoverer = null;
		}
		return this.parameterName;
	}
