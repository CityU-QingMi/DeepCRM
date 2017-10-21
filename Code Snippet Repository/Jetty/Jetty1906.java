    public Name composeName(Name name,
                            Name prefix)
        throws NamingException
    {
        if (name == null)
            throw new NamingException ("Name cannot be null");
        if (prefix == null)
            throw new NamingException ("Prefix cannot be null");

        Name compoundName = (CompoundName)prefix.clone();
        compoundName.addAll (name);
        return compoundName;
    }
