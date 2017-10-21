    public static boolean bindToENC (Object scope, String asName, String mappedName)
    throws NamingException
    {
        if (asName==null||asName.trim().equals(""))
            throw new NamingException ("No name for NamingEntry");

        if (mappedName==null || "".equals(mappedName))
            mappedName=asName;

        NamingEntry entry = lookupNamingEntry (scope, mappedName);
        if (entry == null)
            return false;

        entry.bindToENC(asName);
        return true;
     }
