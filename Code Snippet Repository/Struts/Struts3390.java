    public HttpSession getSession() {
        HttpSession session = null;

        try {
            session = super.getSession();
        } catch (AssertionFailedError e) {
            //ignore
        }

        if (session == null) {
            session = new StrutsMockHttpSession();
            setSession(session);
        }

        return session;
    }
