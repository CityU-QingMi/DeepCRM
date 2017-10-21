    public static Context getContextForScope(Object scope)
    throws NamingException
    {
        InitialContext ic = new InitialContext();
        NameParser parser = ic.getNameParser("");
        Name name = parser.parse("");
        if (scope != null)
        {
            name.add(canonicalizeScope(scope));
        }
        return (Context)ic.lookup(name);
    }
