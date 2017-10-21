    public boolean commit() throws LoginException
    {
        if (!isAuthenticated())
        {
            currentUser = null;
            setCommitted(false);
            return false;
        }

        setCommitted(true);
        currentUser.setJAASInfo(subject);
        return true;
    }
