	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
		if (context != null) {
			User user = (User) session.getAttribute(ChatInterceptor.CHAT_USER_SESSION_KEY);
			if (user != null) {
				ChatService service = (ChatService) context.getBean("chatService");
				service.logout(user.getName());

				LOG.info("session expired, logged user [" + user.getName() + "] out");
			}
		}
	}
