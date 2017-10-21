    public void unbindENC ()
    {
        try
        {
            InitialContext ic = new InitialContext();
            Context env = (Context)ic.lookup("java:comp");
            __log.debug("Unbinding java:comp/"+getJndiName());
            env.unbind(getJndiName());
        }
        catch (NamingException e)
        {
            __log.warn(e);
        }
    }
