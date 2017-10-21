    public static List<Object> lookupNamingEntries (Object scope, Class<?> clazz)
    throws NamingException
    {
        try
        {
            Context scopeContext = getContextForScope(scope);
            Context namingEntriesContext = (Context)scopeContext.lookup(NamingEntry.__contextName);
            ArrayList<Object> list = new ArrayList<Object>();
            lookupNamingEntries(list, namingEntriesContext, clazz);
            return list;
        }
        catch (NameNotFoundException e)
        {
            return Collections.emptyList();
        }
    }
