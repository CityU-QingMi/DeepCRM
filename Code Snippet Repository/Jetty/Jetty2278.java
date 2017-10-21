    public static Name getNameForScope (Object scope)
    {
        try
        {
            InitialContext ic = new InitialContext();
            NameParser parser = ic.getNameParser("");
            Name name = parser.parse("");
            if (scope != null)
            {
                name.add(canonicalizeScope(scope));
            }
            return name;
        }
        catch (NamingException e)
        {
            __log.warn(e);
            return null;
        }
    }
