    public void handle (Callback[] callbacks)
        throws IOException, UnsupportedCallbackException
    {
        for (int i=0; i < callbacks.length; i++)
        {
            if (callbacks[i] instanceof NameCallback)
            {
                ((NameCallback)callbacks[i]).setName(getUserName());
            }
            else if (callbacks[i] instanceof ObjectCallback)
            {
                ((ObjectCallback)callbacks[i]).setObject(getCredential());
            }
            else if (callbacks[i] instanceof PasswordCallback)
            {
                if (getCredential() instanceof Password)
                    ((PasswordCallback)callbacks[i]).setPassword (((Password)getCredential()).toString().toCharArray());
                else if (getCredential() instanceof String)
                {
                    ((PasswordCallback)callbacks[i]).setPassword (((String)getCredential()).toCharArray());
                }
                else
                    throw new UnsupportedCallbackException (callbacks[i], "User supplied credentials cannot be converted to char[] for PasswordCallback: try using an ObjectCallback instead");
            }
            else if (callbacks[i] instanceof RequestParameterCallback)
            {
                RequestParameterCallback callback = (RequestParameterCallback)callbacks[i];
                callback.setParameterValues(Arrays.asList(_request.getParameterValues(callback.getParameterName())));
            }
            else
                throw new UnsupportedCallbackException(callbacks[i]);
        }

    }
