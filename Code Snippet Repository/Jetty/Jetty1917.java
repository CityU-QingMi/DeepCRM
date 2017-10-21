    public Object getObjectInstance(Object ref, Name arg1, Context arg2, Hashtable arg3) throws Exception
    {
        if (ref == null)
        return null;

        Reference reference = (Reference)ref;


        Properties props = new Properties();
        String user = null;
        String password = null;

        Enumeration refs = reference.getAll();
        while (refs.hasMoreElements())
        {
            RefAddr refAddr = (RefAddr)refs.nextElement();
            String name = refAddr.getType();
            String value =  (String)refAddr.getContent();
            if (name.equalsIgnoreCase("user"))
                user = value;
            else if (name.equalsIgnoreCase("pwd"))
                password = value;
            else
                props.put(name, value);
        }

        if (password == null)
            return Session.getInstance(props);
        else
            return Session.getInstance(props, new PasswordAuthenticator(user, password));
    }
