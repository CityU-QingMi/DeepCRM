    public String getNameInNamespace ()
        throws NamingException
    {
        Name name = _parser.parse("");

        NamingContext c = this;
        while (c != null)
        {
            String str = c.getName();
            if (str != null)
                name.add(0, str);
            c = (NamingContext)c.getParent();
        }
        return name.toString();
    }
