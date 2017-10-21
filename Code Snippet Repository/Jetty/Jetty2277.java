    public static Name makeNamingEntryName (NameParser parser, String jndiName)
    throws NamingException
    {
        if (jndiName==null)
            return null;

        if (parser==null)
        {
            InitialContext ic = new InitialContext();
            parser = ic.getNameParser("");
        }

        Name name = parser.parse("");
        name.add(NamingEntry.__contextName);
        name.addAll(parser.parse(jndiName));
        return name;
    }
