    @Override
    public boolean check(Object credentials)
    {
        if (this == credentials)
            return true;

        if (credentials instanceof Password)
            return credentials.equals(_pw);

        if (credentials instanceof String)
            return stringEquals(_pw, (String)credentials);

        if (credentials instanceof char[])
            return stringEquals(_pw, new String((char[])credentials));

        if (credentials instanceof Credential)
            return ((Credential)credentials).check(_pw);

        return false;
    }
