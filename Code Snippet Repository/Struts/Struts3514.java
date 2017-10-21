	@SuppressWarnings("")
	private void restoreStack(ActionInvocation invocation) {
		RenderRequest request = (RenderRequest) invocation.getInvocationContext().get(REQUEST);
		if (StringUtils.isNotEmpty(request.getParameter(EVENT_ACTION))) {
			if(!isProperPrg(invocation)) {
				if (LOG.isDebugEnabled()) LOG.debug("Restoring value stack from event phase");
				ValueStack oldStack = (ValueStack) invocation.getInvocationContext().getSession().get(
				STACK_FROM_EVENT_PHASE);
				if (oldStack != null) {
					CompoundRoot oldRoot = oldStack.getRoot();
					ValueStack currentStack = invocation.getStack();
					CompoundRoot root = currentStack.getRoot();
					root.addAll(0, oldRoot);
					if (LOG.isDebugEnabled()) LOG.debug("Restored stack");
				}
			}
			else {
				if (LOG.isDebugEnabled()) LOG.debug("Won't restore stack from event phase since it's a proper PRG request");
			}
		}
	}
