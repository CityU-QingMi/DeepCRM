    public static boolean validToken() {
        String tokenName = getTokenName();

        if (tokenName == null) {
            LOG.debug("No token name found -> Invalid token ");
            return false;
        }

        String token = getToken(tokenName);

        if (token == null) {
            LOG.debug("No token found for token name {} -> Invalid token ", tokenName);
            return false;
        }

        Map session = ActionContext.getContext().getSession();
		String tokenSessionName = buildTokenSessionAttributeName(tokenName);
        String sessionToken = (String) session.get(tokenSessionName);

        if (!token.equals(sessionToken)) {
            if (LOG.isWarnEnabled()) {
                LocalizedTextProvider localizedTextProvider = ActionContext.getContext().getContainer().getInstance(LocalizedTextProvider.class);
                LOG.warn(localizedTextProvider.findText(TokenHelper.class, "struts.internal.invalid.token", ActionContext.getContext().getLocale(), "Form token {0} does not match the session token {1}.", new Object[]{
                        token, sessionToken
                }));
            }

            return false;
        }

        // remove the token so it won't be used again
        session.remove(tokenSessionName);

        return true;
    }
