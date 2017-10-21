	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		if (!(invocation.getAction() instanceof NoParameters)
				&& (null != this.paramNames)) {
			ActionContext ac = invocation.getInvocationContext();
			HttpParameters parameters = ac.getParameters();

			if (parameters != null) {
                for (String removeName : paramNames) {
					try {
						Parameter parameter = parameters.get(removeName);
						if (parameter.isDefined() && this.paramValues.contains(parameter.getValue())) {
							parameters.remove(removeName);
						}
					} catch (Exception e) {
						LOG.error("Failed to convert parameter to string", e);
					}
                }
			}
		}
		return invocation.invoke();
	}
