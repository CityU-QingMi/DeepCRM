    public void bindEnvEntries (WebAppContext context)
    throws NamingException
    {
        LOG.debug("Binding env entries from the jvm scope");
        InitialContext ic = new InitialContext();
        Context envCtx = (Context)ic.lookup("java:comp/env");
        Object scope = null;
        List<Object> list = NamingEntryUtil.lookupNamingEntries(scope, EnvEntry.class);
        Iterator<Object> itor = list.iterator();
        while (itor.hasNext())
        {
            EnvEntry ee = (EnvEntry)itor.next();
            ee.bindToENC(ee.getJndiName());
            Name namingEntryName = NamingEntryUtil.makeNamingEntryName(null, ee);
            NamingUtil.bind(envCtx, namingEntryName.toString(), ee);//also save the EnvEntry in the context so we can check it later
        }

        LOG.debug("Binding env entries from the server scope");

        scope = context.getServer();
        list = NamingEntryUtil.lookupNamingEntries(scope, EnvEntry.class);
        itor = list.iterator();
        while (itor.hasNext())
        {
            EnvEntry ee = (EnvEntry)itor.next();
            ee.bindToENC(ee.getJndiName());
            Name namingEntryName = NamingEntryUtil.makeNamingEntryName(null, ee);
            NamingUtil.bind(envCtx, namingEntryName.toString(), ee);//also save the EnvEntry in the context so we can check it later
        }

        LOG.debug("Binding env entries from the context scope");
        scope = context;
        list = NamingEntryUtil.lookupNamingEntries(scope, EnvEntry.class);
        itor = list.iterator();
        while (itor.hasNext())
        {
            EnvEntry ee = (EnvEntry)itor.next();
            ee.bindToENC(ee.getJndiName());
            Name namingEntryName = NamingEntryUtil.makeNamingEntryName(null, ee);
            NamingUtil.bind(envCtx, namingEntryName.toString(), ee);//also save the EnvEntry in the context so we can check it later
        }
    }
