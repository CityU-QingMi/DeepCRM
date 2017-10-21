	public static void setSessionToken( String tokenName, String token ) {
		Map<String, Object> session = ActionContext.getContext().getSession();
		try {
			session.put(buildTokenSessionAttributeName(tokenName), token);
		} catch ( IllegalStateException e ) {
			// WW-1182 explain to user what the problem is
            String msg = "Error creating HttpSession due response is committed to client. You can use the CreateSessionInterceptor or create the HttpSession from your action before the result is rendered to the client: " + e.getMessage();
            LOG.error(msg, e);
            throw new IllegalArgumentException(msg);
		}
	}
