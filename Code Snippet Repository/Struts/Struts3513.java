	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		PortletPhase phase = (PortletPhase) invocation.getInvocationContext().get(PortletConstants.PHASE);
		if (phase.isRender()) {
			restoreStack(invocation);
			return invocation.invoke();
		} else if (phase.isAction()) {
			try {
				return invocation.invoke();
			} finally {
				saveStack(invocation);
			}
		} else {
			return invocation.invoke();
		}
	}
