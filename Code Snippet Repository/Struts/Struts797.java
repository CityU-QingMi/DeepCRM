    @Override
    protected String handleValidToken(ActionInvocation invocation) throws Exception {
        // we know the token name and token must be there
        String key = TokenHelper.getTokenName();
        String token = TokenHelper.getToken(key);
		String sessionTokenName = TokenHelper.buildTokenSessionAttributeName(key);
		InvocationSessionStore.storeInvocation(sessionTokenName, token, invocation);

        return invocation.invoke();
    }
