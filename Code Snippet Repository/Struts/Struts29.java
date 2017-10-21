	public String execute() throws Exception {

		User user = (User) session.get(ChatAuthenticationInterceptor.USER_SESSION_KEY);
		try {
			chatService.enterRoom(user, roomName);
		} catch (Exception e) {
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}
