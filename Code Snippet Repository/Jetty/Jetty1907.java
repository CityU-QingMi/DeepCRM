    public String composeName (String name,
                               String prefix)
        throws NamingException
    {
        if (name == null)
            throw new NamingException ("Name cannot be null");
        if (prefix == null)
            throw new NamingException ("Prefix cannot be null");

        Name compoundName = _parser.parse(prefix);
        compoundName.add (name);
        return compoundName.toString();
    }
