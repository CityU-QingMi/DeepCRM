	public String intercept(ActionInvocation invocation) throws Exception {

		LOG.debug("Authenticating chat user");

		SessionMap session = (SessionMap) ActionContext.getContext().get(ActionContext.SESSION);
		User user = (User) session.get(USER_SESSION_KEY);

		if (user == null) {
			return Action.LOGIN;
		}
		return invocation.invoke();
	}
