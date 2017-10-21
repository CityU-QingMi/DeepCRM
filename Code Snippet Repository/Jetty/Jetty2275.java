    public static NamingEntry lookupNamingEntry (Object scope, String jndiName)
    throws NamingException
    {
        NamingEntry entry = null;
        try
        {
            Name scopeName = getNameForScope(scope);
            InitialContext ic = new InitialContext();
            NameParser parser = ic.getNameParser("");
            Name namingEntryName = makeNamingEntryName(parser, jndiName);
            scopeName.addAll(namingEntryName);
            entry =  (NamingEntry)ic.lookup(scopeName);
        }
        catch (NameNotFoundException ee)
        {
        }

        return entry;
    }
