    protected void bindEntry (WebAppContext context, String name, Class<?> typeClass)
    throws Exception
    {
        String nameInEnvironment = name;
        boolean bound = false;

        //check if the name in web.xml has been mapped to something else
        //check a context-specific naming environment first
        Object scope = context;
        NamingEntry ne = NamingEntryUtil.lookupNamingEntry(scope, name);

        if (ne!=null && (ne instanceof Link))
        {
            //if we found a mapping, get out name it is mapped to in the environment
            nameInEnvironment = ((Link)ne).getLink();
        }

        //try finding that mapped name in the webapp's environment first
        scope = context;
        bound = NamingEntryUtil.bindToENC(scope, name, nameInEnvironment);

        if (bound)
            return;

        //try the server's environment
        scope = context.getServer();
        bound = NamingEntryUtil.bindToENC(scope, name, nameInEnvironment);
        if (bound)
            return;

        //try the jvm environment
        bound = NamingEntryUtil.bindToENC(null, name, nameInEnvironment);
        if (bound)
            return;

        //There is no matching resource so try a default name.
        //The default name syntax is: the [res-type]/default
        //eg       javax.sql.DataSource/default
        nameInEnvironment = typeClass.getName()+"/default";
        //First try the server scope
        NamingEntry defaultNE = NamingEntryUtil.lookupNamingEntry(context.getServer(), nameInEnvironment);
        if (defaultNE==null)
            defaultNE = NamingEntryUtil.lookupNamingEntry(null, nameInEnvironment);

        if (defaultNE!=null)
            defaultNE.bindToENC(name);
        else
            throw new IllegalStateException("Nothing to bind for name " + name);
    }
