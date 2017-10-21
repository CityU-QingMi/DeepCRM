	public String execute() throws Exception {

		User user = (User) session.get(ChatAuthenticationInterceptor.USER_SESSION_KEY);
		if (user != null) {
			chatService.logout(user.getName());
			session.remove(ChatAuthenticationInterceptor.USER_SESSION_KEY);
		}

		return SUCCESS;
	}
