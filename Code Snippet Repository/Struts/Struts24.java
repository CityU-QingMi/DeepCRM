	public String execute() throws Exception {
		try {
			chatService.login(new User(name));
			session.put(ChatAuthenticationInterceptor.USER_SESSION_KEY, new User(name));
		} catch (ChatException e) {
			e.printStackTrace();
			addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}
